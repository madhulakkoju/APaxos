package org.cse535.database;

import com.google.protobuf.Timestamp;
import lombok.Data;
import org.cse535.Main;
import org.cse535.configs.GlobalConfigs;
import org.cse535.configs.Utils;
import org.cse535.proto.*;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class DatabaseService {

    //To Send in Prepare Phase Response
    private int AcceptedproposalNumber; // Term Number of previous accepted proposal
    private String AcceptedServerId; // ServerId of previous accepted proposal
    private BlockOfTransactions AcceptedBlockOfTransactions; // Block of Transactions of previous accepted proposal

    private AtomicInteger AccountBalance;

    private AtomicInteger CommittedAccountBalance;
    private int CommittedProposalNumber;

    private HashMap<Integer, BlockOfTransactions> blocks;
    private HashMap<Integer, Integer> balanceAfterCommit;
    private HashMap<String, Integer> clientBalancesAfterCommit;


    private int transactionsProcessed;
    private int transactionsCommitted;
    private int transactionsAborted;

    //These are from Node class
    public int currentProposalNumber; // Term Number of prepare request
    public String currentServerId; // Server Id of prepare request

    public PriorityBlockingQueue<TransactionInputConfig> incomingTransactionsQueue;

    public LocalTransactionStore localTransactionLog;
    public LeaderLocalTnxStore leaderTransactionLog;


    public Timestamp lastPrepareAckTimestamp;
    private String lastPrepareAckServer;

    public Timestamp lastAcceptAckTimestamp;
    public Timestamp lastCommitTimestamp;


    public HashSet<Integer> processedTransactionsSet;
    public HashMap<Integer, TimeTakenToExecute> timeTakenToExecuteMap;






    public DatabaseService(String serverName){
        this.blocks = new HashMap<>();
        this.balanceAfterCommit = new HashMap<>();
        this.balanceAfterCommit.put(0, GlobalConfigs.INIT_BALANCE);

        this.AcceptedBlockOfTransactions = BlockOfTransactions.newBuilder().setTermNumber(0).build();
        this.AcceptedproposalNumber = 0;
        this.AcceptedServerId = "";
        this.AccountBalance = new AtomicInteger(GlobalConfigs.INIT_BALANCE);
        this.CommittedAccountBalance = new AtomicInteger(GlobalConfigs.INIT_BALANCE);


        this.currentServerId = serverName;
        this.incomingTransactionsQueue = new PriorityBlockingQueue<>(100, new Comparator<TransactionInputConfig>() {
            @Override
            public int compare(TransactionInputConfig o1, TransactionInputConfig o2) {
                return o1.getTransaction().getTransactionNum() - o2.getTransaction().getTransactionNum();
            }
        });
        this.localTransactionLog = new LocalTransactionStore();

        this.lastPrepareAckTimestamp = Timestamp.newBuilder().setSeconds(0).setNanos(0).build();
        this.lastAcceptAckTimestamp = Timestamp.newBuilder().setSeconds(0).setNanos(0).build();
        this.lastCommitTimestamp = Timestamp.newBuilder().setSeconds(0).setNanos(0).build();


        this.clientBalancesAfterCommit = new HashMap<>();
        this.processedTransactionsSet = new HashSet<>();
        this.timeTakenToExecuteMap = new HashMap<>();

        GlobalConfigs.allServers.forEach(server -> {
            this.clientBalancesAfterCommit.put(server, GlobalConfigs.INIT_BALANCE);
        });


        this.lastPrepareAckServer = "";



    }

    public BlockOfTransactions getBlock(int termNumber){
        return blocks.get(termNumber);
    }

    public List<BlockOfTransactions> getBlocksFromTerm(int termNumber, int currentTerm){
        List<BlockOfTransactions> blocksToReturn = new ArrayList<>();

        for (int i = termNumber; i <= currentTerm; i++){
            if(blocks.containsKey(i))
                blocksToReturn.add(blocks.get(i));
        }

        return blocksToReturn;
    }

    public HashMap<Integer, BlockOfTransactions> getBlocksFromTermToCurrent(int termNumber){
        HashMap<Integer, BlockOfTransactions> blocksToReturn = new HashMap<>();

        for (int i = termNumber; i <= this.getCommittedProposalNumber(); i++){
            if(blocks.containsKey(i))
                blocksToReturn.put(i, blocks.get(i));
        }

        return blocksToReturn;
    }

    public void updateDatabaseWithBlocks(List<BlockOfTransactions> blocks){
        for (BlockOfTransactions block : blocks){
            this.blocks.put(block.getTermNumber(), block);
            Main.node.logger.log("Block added: " + block.getTermNumber());
        }
    }

    public void commitBlock(int termNumber, BlockOfTransactions block, int balanceAfterTransactions){
        if(block == null) return;

        this.blocks.put(termNumber, block);
        this.setCommittedAccountBalance(balanceAfterTransactions);
        this.CommittedProposalNumber = termNumber;
        this.transactionsCommitted += block.getTransactionsCount();
        Main.node.logger.log("Block committed: " + termNumber);
        this.balanceAfterCommit.put(termNumber, balanceAfterTransactions);

        block.getTransactionsList().forEach(transaction -> {
            this.clientBalancesAfterCommit.put(transaction.getSender(), this.clientBalancesAfterCommit.get(transaction.getSender()) - transaction.getAmount());
            this.clientBalancesAfterCommit.put(transaction.getReceiver(), this.clientBalancesAfterCommit.get(transaction.getReceiver()) + transaction.getAmount());

            if(this.timeTakenToExecuteMap.containsKey(transaction.getTransactionNum())){
                this.timeTakenToExecuteMap.get(transaction.getTransactionNum()).stop();
            }

        });

        this.setAccountBalance(balanceAfterTransactions);




//        System.out.println("Initiating Backup Request for " + this.getCurrentServerId());
//        Main.node.databaseStub.save( DataSaveRequest.newBuilder()
//                        .setServerName(this.getCurrentServerId())
//                        .setSnapshot(this.toSnapshot())
//                .build() );
    }

    public int computeClientsBalance(String clientName){

        if( ! GlobalConfigs.allServers.contains(clientName) ){
            return 0;
        }

        int bal = this.clientBalancesAfterCommit.get(clientName);

        for (int i = 0; i < this.localTransactionLog.getAllTransactions().size(); i++) {
            Transaction transaction = this.localTransactionLog.getAllTransactions().get(i);
            if (transaction.getSender().equals(clientName)) {
                bal -= transaction.getAmount();
            } else if (transaction.getReceiver().equals(clientName)) {
                bal += transaction.getAmount();
            }
        }

        return bal;
    }

    public void uncommitBlock(int termNumber){

        this.transactionsCommitted -= blocks.get(termNumber).getTransactionsCount();
        this.transactionsAborted += blocks.get(termNumber).getTransactionsCount();
        this.blocks.remove(termNumber);

        Main.node.logger.log("Block removed: " + termNumber);
    }











    public int getAcceptedproposalNumber() {
        return AcceptedproposalNumber;
    }

    public void setAcceptedproposalNumber(int acceptedproposalNumber) {
        AcceptedproposalNumber = acceptedproposalNumber;
    }

    public String getAcceptedServerId() {
        return AcceptedServerId;
    }

    public void setAcceptedServerId(String acceptedServerId) {
        AcceptedServerId = acceptedServerId;
    }

    public BlockOfTransactions getAcceptedBlockOfTransactions() {
        return AcceptedBlockOfTransactions;
    }

    public void setAcceptedBlockOfTransactions(BlockOfTransactions acceptedBlockOfTransactions) {
        AcceptedBlockOfTransactions = acceptedBlockOfTransactions;
    }

    public int getAccountBalance() {
        return AccountBalance.get();
    }

    public void setAccountBalance(int accountBalance) {
        AccountBalance.set( accountBalance);
    }

    public int getCommittedAccountBalance() {
        return CommittedAccountBalance.get();
    }

    public void setCommittedAccountBalance(int committedAccountBalance) {
        CommittedAccountBalance.set(committedAccountBalance);
    }

    public int getCommittedProposalNumber() {
        return CommittedProposalNumber;
    }

    public void setCommittedProposalNumber(int committedProposalNumber) {
        CommittedProposalNumber = committedProposalNumber;
    }


    public int getTransactionsAborted() {
        return transactionsAborted;
    }

    public void setTransactionsAborted(int transactionsAborted) {
        this.transactionsAborted = transactionsAborted;
    }

    public int getTransactionsProcessed() {
        return transactionsProcessed;
    }

    public void setTransactionsProcessed(int transactionsProcessed) {
        this.transactionsProcessed = transactionsProcessed;
    }

    public void incrementTransactionsProcessed(){
        this.transactionsProcessed++;
    }

    public int getTransactionsCommitted() {
        return transactionsCommitted;
    }

    public void setTransactionsCommitted(int transactionsCommitted) {
        this.transactionsCommitted = transactionsCommitted;
    }

    public HashMap<Integer, BlockOfTransactions> getBlocks() {
        return blocks;
    }

    public LeaderLocalTnxStore getLeaderTransactionLog() {
        return leaderTransactionLog;
    }

    public void setLeaderTransactionLog(LeaderLocalTnxStore leaderTransactionLog) {
        this.leaderTransactionLog = leaderTransactionLog;
    }

    public LocalTransactionStore getLocalTransactionLog() {
        return localTransactionLog;
    }

    public void setLocalTransactionLog(LocalTransactionStore localTransactionLog) {
        this.localTransactionLog = localTransactionLog;
    }

    public PriorityBlockingQueue<TransactionInputConfig> getIncomingTransactionsQueue() {
        return incomingTransactionsQueue;
    }

    public void setIncomingTransactionsQueue(PriorityBlockingQueue<TransactionInputConfig> incomingTransactionsQueue) {
        this.incomingTransactionsQueue = incomingTransactionsQueue;
    }

    public Timestamp getLastCommitTimestamp() {
        return lastCommitTimestamp;
    }

    public void setLastCommitTimestamp(Timestamp lastCommitTimestamp) {
        this.lastCommitTimestamp = lastCommitTimestamp;
    }

    public Timestamp getLastAcceptAckTimestamp() {
        return lastAcceptAckTimestamp;
    }

    public void setLastAcceptAckTimestamp(Timestamp lastAcceptAckTimestamp) {
        this.lastAcceptAckTimestamp = lastAcceptAckTimestamp;
    }

    public Timestamp getLastPrepareAckTimestamp() {
        return lastPrepareAckTimestamp;
    }

    public void setLastPrepareAckTimestamp(Timestamp lastPrepareAckTimestamp) {
        this.lastPrepareAckTimestamp = lastPrepareAckTimestamp;
    }

    public void setLastPrepareAckServerId(String processId) {
        this.lastPrepareAckServer = processId;
    }


    public void setBlocks(HashMap<Integer, BlockOfTransactions> blocks) {
        this.blocks = blocks;
    }

    public HashMap<Integer, Integer> getBalanceAfterCommit() {
        return balanceAfterCommit;
    }

    public void setBalanceAfterCommit(HashMap<Integer, Integer> balanceAfterCommit) {
        this.balanceAfterCommit = balanceAfterCommit;
    }

    public HashMap<String, Integer> getClientBalancesAfterCommit() {
        return clientBalancesAfterCommit;
    }

    public void setClientBalancesAfterCommit(HashMap<String, Integer> clientBalancesAfterCommit) {
        this.clientBalancesAfterCommit = clientBalancesAfterCommit;
    }

    public int getCurrentProposalNumber() {
        return currentProposalNumber;
    }

    public void setCurrentProposalNumber(int currentProposalNumber) {
        this.currentProposalNumber = currentProposalNumber;
    }

    public String getCurrentServerId() {
        return currentServerId;
    }

    public void setCurrentServerId(String currentServerId) {
        this.currentServerId = currentServerId;
    }

    public String getLastPrepareAckServer() {
        return lastPrepareAckServer;
    }

    public void setLastPrepareAckServer(String lastPrepareAckServer) {
        this.lastPrepareAckServer = lastPrepareAckServer;
    }


    public void setAccountBalance(AtomicInteger accountBalance) {
        AccountBalance = accountBalance;
    }

    public void setCommittedAccountBalance(AtomicInteger committedAccountBalance) {
        CommittedAccountBalance = committedAccountBalance;
    }

    public HashSet<Integer> getProcessedTransactionsSet() {
        return processedTransactionsSet;
    }

    public void setProcessedTransactionsSet(HashSet<Integer> processedTransactionsSet) {
        this.processedTransactionsSet = processedTransactionsSet;
    }

    public HashMap<Integer, TimeTakenToExecute> getTimeTakenToExecuteMap() {
        return timeTakenToExecuteMap;
    }

    public void setTimeTakenToExecuteMap(HashMap<Integer, TimeTakenToExecute> timeTakenToExecuteMap) {
        this.timeTakenToExecuteMap = timeTakenToExecuteMap;
    }

    public DatabaseSnapshot toSnapshot(){
        return DatabaseSnapshot.newBuilder()
                .setAcceptedProposalNumber(this.getAcceptedproposalNumber())
                .setAcceptedServerId(this.getAcceptedServerId())
                .setAcceptedBlockOfTransactions(this.getAcceptedBlockOfTransactions())
                .setAccountBalance(this.getAccountBalance())
                .setCommittedAccountBalance(this.getCommittedAccountBalance())
                .setCommittedProposalNumber(this.getCommittedProposalNumber())
                .putAllBlocks(this.getBlocks())
                .putAllBalanceAfterCommit(this.getBalanceAfterCommit())
                .putAllClientBalancesAfterCommit(this.getClientBalancesAfterCommit())
                .setTransactionsProcessed(this.getTransactionsProcessed())
                .setTransactionsCommitted(this.getTransactionsCommitted())
                .setTransactionsAborted(this.getTransactionsAborted())
                .setCurrentProposalNumber(this.getCurrentProposalNumber())
                .setCurrentServerId(this.getCurrentServerId())
                .addAllIncomingTransactionsQueue(this.getIncomingTransactionsQueue())
                .addAllLocalTransactionLog(this.getLocalTransactionLog().getAllTransactions())
                .addAllLeaderTransactionLog(this.getLeaderTransactionLog().getAllTransactions())
                .setLastPrepareAckTimestamp(this.getLastPrepareAckTimestamp())
                .setLastPrepareAckServer(this.getLastPrepareAckServer())
                .setLastAcceptAckTimestamp(this.getLastAcceptAckTimestamp())
                .setLastCommitTimestamp(this.getLastCommitTimestamp())
                .addAllProcessedTransactionsSet(this.getProcessedTransactionsSet())
                .putAllTimeTakenToExecuteMap(Utils.toTimeTakenMask( this.getTimeTakenToExecuteMap()))
                .setLeaderBalanceAfterTransactions(this.getLeaderTransactionLog().BalanceAfterTransactions)
                .build();
    }

    public static DatabaseService toDatabaseService(DatabaseSnapshot snapshot){


        PriorityBlockingQueue<TransactionInputConfig> q = new PriorityBlockingQueue<>(100, new Comparator<TransactionInputConfig>() {
            @Override
            public int compare(TransactionInputConfig o1, TransactionInputConfig o2) {
                return o1.getTransaction().getTransactionNum() - o2.getTransaction().getTransactionNum();
            }
        } );

        q.addAll(snapshot.getIncomingTransactionsQueueList());


        DatabaseService databaseService = new DatabaseService(snapshot.getCurrentServerId());
        databaseService.setAcceptedproposalNumber(snapshot.getAcceptedProposalNumber());
        databaseService.setAcceptedServerId(snapshot.getAcceptedServerId());
        databaseService.setAcceptedBlockOfTransactions(snapshot.getAcceptedBlockOfTransactions());
        databaseService.setAccountBalance(snapshot.getAccountBalance());
        databaseService.setCommittedAccountBalance(snapshot.getCommittedAccountBalance());
        databaseService.setCommittedProposalNumber(snapshot.getCommittedProposalNumber());
        databaseService.setBlocks(new HashMap<>(snapshot.getBlocksMap()));
        databaseService.setBalanceAfterCommit(new HashMap<>(snapshot.getBalanceAfterCommitMap()));
        databaseService.setClientBalancesAfterCommit(new HashMap<>(snapshot.getClientBalancesAfterCommitMap()));
        databaseService.setTransactionsProcessed(snapshot.getTransactionsProcessed());
        databaseService.setTransactionsCommitted(snapshot.getTransactionsCommitted());
        databaseService.setTransactionsAborted(snapshot.getTransactionsAborted());
        databaseService.setCurrentProposalNumber(snapshot.getCurrentProposalNumber());
        databaseService.setCurrentServerId(snapshot.getCurrentServerId());
        databaseService.setIncomingTransactionsQueue( q );
        databaseService.setLocalTransactionLog(new LocalTransactionStore(snapshot.getLocalTransactionLogList()));
        databaseService.setLeaderTransactionLog(new LeaderLocalTnxStore(Main.node, snapshot.getLeaderTransactionLogList(), snapshot.getLeaderBalanceAfterTransactions()));
        databaseService.setLastPrepareAckTimestamp(snapshot.getLastPrepareAckTimestamp());
        databaseService.setLastPrepareAckServer(snapshot.getLastPrepareAckServer());
        databaseService.setLastAcceptAckTimestamp(snapshot.getLastAcceptAckTimestamp());
        databaseService.setLastCommitTimestamp(snapshot.getLastCommitTimestamp());
        databaseService.setProcessedTransactionsSet(new HashSet<>(snapshot.getProcessedTransactionsSetList()));
        databaseService.setTimeTakenToExecuteMap(Utils.toTimeTakenMap(snapshot.getTimeTakenToExecuteMapMap()));

        return databaseService;

    }
}
