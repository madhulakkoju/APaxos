package org.cse535.node;


import org.cse535.configs.GlobalConfigs;
import org.cse535.configs.Utils;
import org.cse535.database.LeaderLocalTnxStore;
import org.cse535.proto.*;
import org.cse535.threadimpls.AcceptWorkerThread;
import org.cse535.threadimpls.CommitWorkerThread;
import org.cse535.threadimpls.PrepareWorkerThread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.protobuf.util.Timestamps.fromMillis;
import static java.lang.System.currentTimeMillis;
import static org.cse535.configs.GlobalConfigs.serversToPortMap;

public class Node extends NodeServer{




    //TODO: Convert to Database Service class.



    //TODO: Till here







    public Thread transactionWorkerThread;

    public HashMap<String, PrepareResponse> prepareResponseMap;
    public HashMap<String, AcceptResponse> acceptResponseMap;
    public HashMap<String, CommitResponse> commitResponseMap;




    public int prepareAckCount; // Prepare Accepted Servers count
    public int prepareTotalCount; // Current Active Servers

    public boolean pauseTnxServiceUntilCommit;


    public Node(String serverName, int port){
        super(serverName, port);


        this.transactionWorkerThread = new Thread(this::processTnxsInQueue);

        this.prepareResponseMap = new HashMap<>();
        this.acceptResponseMap = new HashMap<>();
        this.commitResponseMap = new HashMap<>();

        this.pauseTnxServiceUntilCommit = false;
        this.database.setLeaderTransactionLog(new LeaderLocalTnxStore(this));


        try {
            this.server.start();
            this.transactionWorkerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processTnxsInQueue() {
        logger.log("Transaction Worker Thread Started");
        while (true) {
            try {
                if(pauseTnxServiceUntilCommit){
                    logger.log("Pausing Transaction Service until Commit");
                    Thread.sleep(10);
                    continue;
                }

                TransactionInputConfig transactionInput = this.database.incomingTransactionsQueue.take();

                if( ! transactionInput.getTransaction().getSender().equals(this.serverName) ){
                    this.logger.log("Transaction not for this client / server");
                    continue;
                }

                this.currentActiveServers = transactionInput.getServerNamesList();

                Transaction transaction = transactionInput.getTransaction();
                boolean isSuccessWithoutConsensus = processTransaction(transaction);

                if( ! isSuccessWithoutConsensus ) {
                    // Need Consensus
                    this.logger.log("Need Consensus for Transaction: " + transaction.toString());
                    boolean prepareSuccess = initiatePreparePhase(transactionInput);
                    // By now, Leader Local log is updated with all the transactions from majority servers

                    if (prepareSuccess){
                        // send compiled list to all servers for accept phase
                        this.logger.log("Prepare Phase Success");
                        boolean acceptSuccess = initiateAcceptPhase();

                        if(acceptSuccess){
                            // send Commit message to all servers
                            this.logger.log("Accept Phase Success");
                            this.logger.log("Initiating Commit Phase");
                            boolean commitSuccess = initiateCommitPhase();

                            this.pauseTnxServiceUntilCommit = false;

                        }

                    }
                    else {
                        this.logger.log("Prepare Phase Failed");

                        Thread.sleep(GlobalConfigs.PHASE_TIMEOUT);
                    }

                    this.database.getIncomingTransactionsQueue().add(transactionInput);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean processTransaction(Transaction transaction) {
        // Process Transaction
        this.logger.log("Processing Transaction: " + transaction.toString());

        if(this.database.getAccountBalance() > transaction.getAmount()) {
            this.logger.log("Transaction Accepted");
            this.database.setAccountBalance( this.database.getAccountBalance() - transaction.getAmount());
            this.database.localTransactionLog.addTransaction(transaction);
            this.database.incrementTransactionsProcessed();
            return true; // No Consensus
        }
        this.logger.log("Transaction Rejected -> Directed to Consensus");
        return false;
    }

    public void addTransactionToQueue(TransactionInputConfig transactionInput){
        this.database.incomingTransactionsQueue.add(transactionInput);
    }



    // Leader Side

    public boolean initiatePreparePhase( TransactionInputConfig transactionInput) {
        try {
            this.prepareResponseMap.clear();

            this.prepareTotalCount = transactionInput.getServerNamesCount();
            this.prepareAckCount = 0;

            this.database.currentProposalNumber = this.database.getAcceptedproposalNumber() + 1;

            PrepareRequest prepareRequest = PrepareRequest.newBuilder()
                    .setProposalNumber(this.database.currentProposalNumber)
                    .setProcessId(this.database.currentServerId)
                    .setTimestamp(fromMillis(currentTimeMillis()))
                    .build();

            Thread[] prepareThreads = new PrepareWorkerThread[this.currentActiveServers.size()];

            for(int i=0;i< this.currentActiveServers.size() ;i++){
                prepareThreads[i] = new PrepareWorkerThread(this,
                        serversToPortMap.get(transactionInput.getServerNames(i)),
                        transactionInput.getServerNames(i),
                        prepareRequest);
            }

            for(int i=0;i< this.currentActiveServers.size() ;i++){
                prepareThreads[i].start();
            }

            for(int i=0;i< this.currentActiveServers.size();i++){
                prepareThreads[i].join();
            }

            if(prepareAckCount > prepareTotalCount/2){

                // Majority have accepted the proposal.
                // Now, we need to check if the current Node (Leader is on the same page with the majority)
                this.database.leaderTransactionLog.clearTransactions();
                //Leader local tnxs are added to compiled list
                this.database.leaderTransactionLog.addAllTransactions(this.database.localTransactionLog.getAllTransactions());
                this.prepareResponseMap.forEach((serverName, prepareResponse) -> {

                    if(prepareResponse.getSuccess()){
                        this.database.leaderTransactionLog.addAllTransactions(prepareResponse.getTransactionsList());
                    }

                });

                // we now have prepared a compiled list of transactions.
                this.database.leaderTransactionLog.reorderTransactionsBasedOnTimestamp();
                this.database.leaderTransactionLog.computeBalanceAfterTransactions();

                this.database.localTransactionLog.clearTransactions();

                // Next, we need to send this compiled list to all the servers for acceptance.
                return true;
            }

            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean initiateAcceptPhase(){

        try {

            this.acceptResponseMap.clear();

            int minCommittedTermNumber = this.database.getCommittedProposalNumber();

            for (Map.Entry<String, PrepareResponse> entry : prepareResponseMap.entrySet()) {
                PrepareResponse prepareResponse = entry.getValue();
                if (prepareResponse.getSuccess()) {
                    minCommittedTermNumber = Math.min(minCommittedTermNumber, prepareResponse.getAcceptNumProposalNumber());
                }
            }




            AcceptRequest acceptRequest = AcceptRequest.newBuilder()
                    .setProposalNumber(this.database.currentProposalNumber)
                    .setProcessId(this.database.currentServerId)
                    .setTimestamp(fromMillis(currentTimeMillis()))
                    .putAllSyncBlocks(this.database.getBlocksFromTermToCurrent(minCommittedTermNumber))
                    .setNeedsSync( minCommittedTermNumber != this.database.getCommittedProposalNumber() )
                    .addAllTransactionsToAccept(this.database.leaderTransactionLog.getAllTransactions())
                    .build();

            Thread[] acceptThreads = new AcceptWorkerThread[this.currentActiveServers.size()];

            for(int i=0;i< this.currentActiveServers.size();i++){
                acceptThreads[i] = new AcceptWorkerThread(this,
                        serversToPortMap.get( this.currentActiveServers.get(i)) ,
                        this.currentActiveServers.get(i),
                        acceptRequest);
            }

            this.pauseTnxServiceUntilCommit = true;

            for (Thread acceptThread : acceptThreads) {
                if(acceptThread == null)
                    continue;
                acceptThread.start();
            }

            for (Thread acceptThread : acceptThreads) {
                if(acceptThread == null)
                    continue;
                acceptThread.join();
            }

            return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean initiateCommitPhase(){

        try{

            this.commitResponseMap.clear();

                CommitRequest commitRequest = CommitRequest.newBuilder()
                        .setProposalNumber(this.database.currentProposalNumber)
                        .setProcessId(this.database.currentServerId)
                        .setBlock(this.database.leaderTransactionLog.convertToBlockOfTransactions(this.database.currentProposalNumber))
                        .setTimestamp(fromMillis(currentTimeMillis()))
                        .build();

                Thread[] commitThreads = new Thread[serversToPortMap.size()];

                for(int i=0;i< this.currentActiveServers.size();i++){
                    commitThreads[i] = new CommitWorkerThread(this,
                            serversToPortMap.get( this.currentActiveServers.get(i)) ,
                            this.currentActiveServers.get(i),
                            commitRequest);
                }

                for(int i=0;i< this.currentActiveServers.size();i++){
                    commitThreads[i].start();
                }

                for(int i=0;i< this.currentActiveServers.size();i++){
                    commitThreads[i].join();
                }

                this.logger.log("Commit Phase Completed");
                return true;
        }
        catch (InterruptedException e) {
                e.printStackTrace();
        }

        return false;
    }






    // Candidates Side

    public PrepareResponse handlePreparePhase(PrepareRequest request) {
        PrepareResponse.Builder prepareResponse = PrepareResponse.newBuilder();
        this.logger.log("Prepare Request Received from " + request.getProcessId() );

        this.logger.log("Prepare Request --> Request Proposal Number: " + request.getProposalNumber()
                + " DB Accepted Proposal Number: " + this.database.getAcceptedproposalNumber() );

        if( request.getProposalNumber() > this.database.getAcceptedproposalNumber()
//              && (this.database.getLastPrepareAckTimestamp().getSeconds() >  request.getTimestamp().getSeconds() ||
//                        ( this.database.getLastPrepareAckTimestamp().getSeconds() == request.getTimestamp().getSeconds() &&
//                                this.database.getLastPrepareAckTimestamp().getNanos() > request.getTimestamp().getNanos() ) )
        ) {
            prepareResponse.setProposalNumber(request.getProposalNumber());
            prepareResponse.setProcessId(request.getProcessId());
            prepareResponse.setSuccess(true);

            if(this.database.localTransactionLog.getAllTransactions() != null)
                prepareResponse.addAllTransactions(this.database.localTransactionLog.getAllTransactions());

            prepareResponse.setAcceptNumProposalNumber(this.database.getAcceptedproposalNumber());

            if(this.database.getAcceptedServerId() != null)
                prepareResponse.setAcceptNumProcessId(this.database.getAcceptedServerId());

            if(this.database.getAcceptedBlockOfTransactions() != null)
                prepareResponse.setAcceptVal(this.database.getAcceptedBlockOfTransactions());

            this.database.setLastPrepareAckTimestamp(request.getTimestamp());

            this.database.setLastPrepareAckServerId(request.getProcessId());
        }
        else{
            prepareResponse.setSuccess(false);
        }
        return prepareResponse.build();
    }

    public AcceptResponse handleAcceptPhase(AcceptRequest request) {
        AcceptResponse.Builder acceptResponse = AcceptResponse.newBuilder();

        boolean acceptProposal = false;

        if(this.getDatabase().getLastPrepareAckServer().equals(request.getProcessId())){
            // Candidate is up-to-date with the Leader's previous blocks
            if(request.getProposalNumber() == this.database.getAcceptedproposalNumber() + 1){
                acceptProposal = true;
                acceptResponse.setSuccess(true);

            }
            else if( request.getProposalNumber() > this.database.getAcceptedproposalNumber()){

                for(int i = this.database.getCommittedProposalNumber()+1; i < request.getProposalNumber(); i++){

                    this.database.commitBlock(i,
                            request.getSyncBlocksMap().get(i),
                            recomputeBalanceBeforeCommit(request.getSyncBlocksMap().get(i),
                                    this.database.getCommittedAccountBalance())
                    );
                }

                acceptProposal = true;
            }
            else{
                acceptResponse.setSuccess(false);
            }
        }
        else{
            acceptResponse.setSuccess(false);
        }

        if(acceptProposal){

            this.database.leaderTransactionLog.clearTransactions();
            this.database.leaderTransactionLog.addAllTransactions(request.getTransactionsToAcceptList());
            this.database.leaderTransactionLog.reorderTransactionsBasedOnTimestamp();
            this.database.leaderTransactionLog.computeBalanceAfterTransactions();

            this.database.setAcceptedBlockOfTransactions(
                    this.database.leaderTransactionLog.convertToBlockOfTransactions(request.getProposalNumber())
            );

            this.database.setAcceptedproposalNumber(request.getProposalNumber());
            this.database.setAcceptedServerId(request.getProcessId());
            this.database.setAccountBalance(this.database.leaderTransactionLog.BalanceAfterTransactions);

            acceptResponse.setSuccess(true);
            acceptResponse.setProposalNumber(request.getProposalNumber());
            acceptResponse.setProcessId(request.getProcessId());
            acceptResponse.setAcceptedServerName(this.database.currentServerId);

            this.database.setLastAcceptAckTimestamp(request.getTimestamp());
            this.database.localTransactionLog.clearTransactions();
        }

        return acceptResponse.build();
    }

    public CommitResponse handleCommitPhase(CommitRequest request) {
        CommitResponse.Builder commitResponse = CommitResponse.newBuilder();

        if(request.getProposalNumber() == this.database.getAcceptedproposalNumber()){

            this.database.commitBlock(request.getProposalNumber(), request.getBlock(),
                    recomputeBalanceBeforeCommit(request.getBlock(), this.database.getCommittedAccountBalance()));

            this.database.currentProposalNumber = this.database.getCommittedProposalNumber();

            commitResponse.setSuccess(true);
            commitResponse.setProposalNumber(request.getProposalNumber());
            commitResponse.setProcessId(request.getProcessId());
            commitResponse.setAcceptedServerName(this.database.currentServerId);

            this.database.setLastCommitTimestamp(request.getTimestamp());

        }else{
            commitResponse.setSuccess(false);
        }

        return commitResponse.build();
    }





    public int recomputeBalanceBeforeCommit(BlockOfTransactions block, int committedAccountBalance){
        int balance = committedAccountBalance;

        if(block != null) {
            for (Transaction transaction : block.getTransactionsList()) {

                if (transaction.getSender().equals(this.serverName))
                    balance -= transaction.getAmount();

                else if (transaction.getReceiver().equals(this.serverName))
                    balance += transaction.getAmount();

            }
        }
        return balance;
    }








    //Commands

    public void printBalance() {
        this.logger.log("Account Balance: " + database.getAccountBalance());
        this.logger.log("Committed Account Balance: " + database.getCommittedAccountBalance());
    }

    public void printLog(){

        this.logger.log( "Current Local Balance: " + this.database.getAccountBalance() );
        this.logger.log( "-------------------" );

        this.logger.log( "Current Local Log Transactions: " );
        this.logger.log( "-------------------" );
        this.database.localTransactionLog.getAllTransactions().forEach( transaction -> {
            this.logger.log( transaction.toString() );
        });
    }

    public void printDB(){

        this.logger.log( "Committed Balance: " + this.database.getCommittedAccountBalance() );
        this.logger.log( "-------------------" );

        this.logger.log( "Committed Blocks: " );
        this.logger.log( "-------------------" );

//        this.database.getBlocks().forEach( (term,block) -> {
//            this.logger.log("Term : "+ term + "\nBlock:\n" + block.toString() );
//        });

        Utils.toString(this.database.getBlocks());

        this.logger.log( "-------------------" );

        this.logger.log( "Current Local Balance: " + this.database.getAccountBalance() );
        this.logger.log( "-------------------" );

        this.logger.log( "Current Local Log Transactions: " );
        this.logger.log( "-------------------" );

        this.database.localTransactionLog.getAllTransactions().forEach( transaction -> {
            this.logger.log( transaction.toString() );
        });
    }

    public void printPerformance(){
        this.logger.log("Transactions Processed: " + this.database.getTransactionsProcessed());
        this.logger.log("Transactions Committed: " + this.database.getTransactionsCommitted());
        this.logger.log("Transactions Aborted: " + this.database.getTransactionsAborted());
    }





}
