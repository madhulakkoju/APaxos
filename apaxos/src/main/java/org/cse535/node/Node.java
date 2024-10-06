package org.cse535.node;


import org.cse535.database.LeaderLocalTnxStore;
import org.cse535.database.LocalTransactionStore;
import org.cse535.proto.*;
import org.cse535.threadimpls.AcceptWorkerThread;
import org.cse535.threadimpls.CommitWorkerThread;
import org.cse535.threadimpls.PrepareWorkerThread;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

import static com.google.protobuf.util.Timestamps.fromMillis;
import static java.lang.System.currentTimeMillis;
import static org.cse535.configs.GlobalConfigs.serversToPortMap;

public class Node extends NodeServer{

    public int currentProposalNumber; // Term Number of prepare request
    public String currentServerId; // Server Id of prepare request

    public PriorityBlockingQueue<TransactionInputConfig> incomingTransactionsQueue;

    public LocalTransactionStore localTransactionLog;
    public LeaderLocalTnxStore leaderTransactionLog;

    public Thread transactionWorkerThread;



    public HashMap<String, PrepareResponse> prepareResponseMap;
    public HashMap<String, AcceptResponse> acceptResponseMap;
    public HashMap<String, CommitResponse> commitResponseMap;

    public boolean pauseTnxServiceUntilCommit;

    public int prepareAckCount; // Prepare Accepted Servers count
    public int prepareTotalCount; // Current Active Servers

    public Node(String serverName, int port){
        super(serverName, port);
        this.currentServerId = serverName;
        this.incomingTransactionsQueue = new PriorityBlockingQueue<>();
        this.transactionWorkerThread = new Thread(this::processTnxsInQueue);
        this.localTransactionLog = new LocalTransactionStore();
        this.leaderTransactionLog = new LeaderLocalTnxStore(this);
        this.prepareResponseMap = new HashMap<>();
        this.pauseTnxServiceUntilCommit = false;

        try {
            this.server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processTnxsInQueue() {
        while (true) {
            try {
                if(pauseTnxServiceUntilCommit){
                    Thread.sleep(100);
                    continue;
                }

                TransactionInputConfig transactionInput = incomingTransactionsQueue.take();

                this.currentActiveServers = transactionInput.getServerNamesList();

                Transaction transaction = transactionInput.getTransaction();
                boolean isSuccessWithoutConsensus = processTransaction(transaction);

                if( ! isSuccessWithoutConsensus ) {

                    boolean prepareSuccess = initiatePreparePhase(transactionInput);
                    // By now, Leader Local log is updated with all the transactions from majority servers

                    if (prepareSuccess){
                        // send compiled list to all servers for accept phase

                        boolean acceptSuccess = initiateAcceptPhase();

                        if(acceptSuccess){
                            // send Commit message to all servers

                            boolean commitSuccess = initiateCommitPhase();

                            this.pauseTnxServiceUntilCommit = false;

                        }

                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean processTransaction(Transaction transaction) {
        // Process Transaction

        if(this.database.getAccountBalance() > transaction.getAmount()) {
            this.database.setAccountBalance( this.database.getAccountBalance() - transaction.getAmount());
            localTransactionLog.addTransaction(transaction);
            return true; // No Consensus
        }

        return false;

    }

    public void addTransactionToQueue(TransactionInputConfig transactionInput){
        this.incomingTransactionsQueue.add(transactionInput);
    }



    // Leader Side

    public boolean initiatePreparePhase( TransactionInputConfig transactionInput) {
        try {
            this.prepareResponseMap.clear();

            this.prepareTotalCount = transactionInput.getServerNamesCount();
            this.prepareAckCount = 0;

            this.currentProposalNumber = this.database.getAcceptedproposalNumber() + 1;

            PrepareRequest prepareRequest = PrepareRequest.newBuilder()
                    .setProposalNumber(currentProposalNumber)
                    .setProcessId(currentServerId)
                    .setTimestamp(fromMillis(currentTimeMillis()))
                    .build();

            Thread[] prepareThreads = new PrepareWorkerThread[serversToPortMap.size()];

            for(int i=0;i< transactionInput.getServerNamesCount();i++){
                prepareThreads[i] = new PrepareWorkerThread(this,
                        serversToPortMap.get(transactionInput.getServerNames(i)),
                        transactionInput.getServerNames(i),
                        prepareRequest);
            }

            for(int i=0;i< transactionInput.getServerNamesCount();i++){
                prepareThreads[i].start();
            }

            for(int i=0;i< transactionInput.getServerNamesCount();i++){
                prepareThreads[i].join();
            }

            if(prepareAckCount > prepareTotalCount/2){

                // Majority have accepted the proposal.
                // Now, we need to check if the current Node (Leader is on the same page with the majority)


                this.leaderTransactionLog.clearTransactions();

                //Leader local tnxs are added to compiled list
                this.leaderTransactionLog.addAllTransactions(localTransactionLog.getAllTransactions());


                this.prepareResponseMap.forEach((serverName, prepareResponse) -> {

                    if(prepareResponse.getSuccess()){
                        this.leaderTransactionLog.addAllTransactions(prepareResponse.getTransactionsList());
                    }

                });

                // we now have prepared a compiled list of transactions.
                this.leaderTransactionLog.reorderTransactionsBasedOnTimestamp();

                this.leaderTransactionLog.computeBalanceAfterTransactions();

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

            AcceptRequest acceptRequest = AcceptRequest.newBuilder()
                    .setProposalNumber(currentProposalNumber)
                    .setProcessId(currentServerId)
                    .setTimestamp(fromMillis(currentTimeMillis()))
                    .addAllTransactionsToAccept(leaderTransactionLog.getAllTransactions())
                    .build();

            Thread[] acceptThreads = new AcceptWorkerThread[serversToPortMap.size()];

            for(int i=0;i< this.currentActiveServers.size();i++){
                acceptThreads[i] = new AcceptWorkerThread(this,
                        serversToPortMap.get( this.currentActiveServers.get(i)) ,
                        this.currentActiveServers.get(i),
                        acceptRequest);
            }

            this.pauseTnxServiceUntilCommit = true;

            for(int i=0;i< serversToPortMap.size();i++){
                acceptThreads[i].start();
            }

            for(int i=0;i< serversToPortMap.size();i++){
                acceptThreads[i].join();
            }

            return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean initiateCommitPhase(){

        try{
                CommitRequest commitRequest = CommitRequest.newBuilder()
                        .setProposalNumber(currentProposalNumber)
                        .setProcessId(currentServerId)
                        .setBlock(leaderTransactionLog.convertToBlockOfTransactions(this.currentProposalNumber))
                        .setTimestamp(fromMillis(currentTimeMillis()))
                        .build();

                Thread[] commitThreads = new Thread[serversToPortMap.size()];

                for(int i=0;i< this.currentActiveServers.size();i++){
                    commitThreads[i] = new CommitWorkerThread(this,
                            serversToPortMap.get( this.currentActiveServers.get(i)) ,
                            this.currentActiveServers.get(i),
                            commitRequest);
                }

                for(int i=0;i< serversToPortMap.size();i++){
                    commitThreads[i].start();
                }

                for(int i=0;i< serversToPortMap.size();i++){
                    commitThreads[i].join();
                }

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

        if(request.getProposalNumber() > this.database.getAcceptedproposalNumber()){

            prepareResponse.setProposalNumber(request.getProposalNumber());
            prepareResponse.setProcessId(request.getProcessId());
            prepareResponse.addAllTransactions(this.localTransactionLog.getAllTransactions());
            prepareResponse.setAcceptNumProposalNumber(this.database.getAcceptedproposalNumber());
            prepareResponse.setAcceptNumProcessId(this.database.getAcceptedServerId());
            prepareResponse.setAcceptVal(this.database.getAcceptedBlockOfTransactions());
            prepareResponse.setSuccess(true);

        }
        else{
            prepareResponse.setSuccess(false);
        }

        return prepareResponse.build();
    }

    public AcceptResponse handleAcceptPhase(AcceptRequest request) {
        AcceptResponse.Builder acceptResponse = AcceptResponse.newBuilder();

        boolean acceptProposal = false;

        // Candidate is up-to-date with the Leader's previous blocks
        if(request.getProposalNumber() == this.database.getAcceptedproposalNumber() + 1){
            acceptProposal = true;
            acceptResponse.setSuccess(true);

        }
        else if( request.getProposalNumber() > this.database.getAcceptedproposalNumber()){

            for(int i = this.database.getCommittedProposalNumber()+1; i < request.getProposalNumber(); i++){

                this.database.commitBlock(i,
                        request.getSyncBlocksMap().get(i),
                        recomputeBalanceBeforeCommit(request.getSyncBlocksMap().get(i), this.database.getCommittedAccountBalance())
                );
            }

            acceptProposal = true;
        }
        else{
            acceptResponse.setSuccess(false);
        }


        if(acceptProposal){

            this.leaderTransactionLog.clearTransactions();
            this.leaderTransactionLog.addAllTransactions(request.getTransactionsToAcceptList());
            this.leaderTransactionLog.reorderTransactionsBasedOnTimestamp();
            this.leaderTransactionLog.computeBalanceAfterTransactions();

            this.database.setAcceptedBlockOfTransactions(
                    this.leaderTransactionLog.convertToBlockOfTransactions(request.getProposalNumber())
            );

            this.database.setAcceptedproposalNumber(request.getProposalNumber());
            this.database.setAcceptedServerId(request.getProcessId());
            this.database.setAccountBalance(this.leaderTransactionLog.BalanceAfterTransactions);

            acceptResponse.setSuccess(true);
            acceptResponse.setProposalNumber(request.getProposalNumber());
            acceptResponse.setProcessId(request.getProcessId());
            acceptResponse.setAcceptedServerName(this.currentServerId);
        }

        return acceptResponse.build();
    }

    public CommitResponse handleCommitPhase(CommitRequest request) {
        CommitResponse.Builder commitResponse = CommitResponse.newBuilder();

        if(request.getProposalNumber() == this.database.getAcceptedproposalNumber()){

            this.database.commitBlock(request.getProposalNumber(), request.getBlock(),
                    recomputeBalanceBeforeCommit(request.getBlock(), this.database.getCommittedAccountBalance()));

            this.currentProposalNumber = this.database.getCommittedProposalNumber();

            commitResponse.setSuccess(true);
            commitResponse.setProposalNumber(request.getProposalNumber());
            commitResponse.setProcessId(request.getProcessId());
            commitResponse.setAcceptedServerName(this.currentServerId);

        }else{
            commitResponse.setSuccess(false);
        }

        return commitResponse.build();
    }





    public int recomputeBalanceBeforeCommit(BlockOfTransactions block, int committedAccountBalance){
        int balance = committedAccountBalance;

        for(Transaction transaction : block.getTransactionsList()){

            if(transaction.getSender().equals(this.serverName))
                balance -= transaction.getAmount();

            else if(transaction.getReceiver().equals(this.serverName))
                balance += transaction.getAmount();

        }

        return balance;
    }






}
