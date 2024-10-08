package org.cse535.database;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.cse535.Main;
import org.cse535.configs.GlobalConfigs;
import org.cse535.node.Node;
import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.TransactionInputConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

@Data
public class DatabaseService {

    //To Send in Prepare Phase Response
    private int AcceptedproposalNumber; // Term Number of previous accepted proposal

    private String AcceptedServerId; // ServerId of previous accepted proposal

    private BlockOfTransactions AcceptedBlockOfTransactions; // Block of Transactions of previous accepted proposal

    private int AccountBalance;

    private int CommittedAccountBalance;
    private int CommittedProposalNumber;

    private HashMap<Integer, BlockOfTransactions> blocks;



    private int transactionsProcessed;
    private int transactionsCommitted;
    private int transactionsAborted;




    //These are from Node class
    public int currentProposalNumber; // Term Number of prepare request
    public String currentServerId; // Server Id of prepare request

    public PriorityBlockingQueue<TransactionInputConfig> incomingTransactionsQueue;

    public LocalTransactionStore localTransactionLog;
    public LeaderLocalTnxStore leaderTransactionLog;







    public DatabaseService(String serverName){
        this.blocks = new HashMap<>();
        this.AcceptedBlockOfTransactions = null;
        this.AcceptedproposalNumber = 0;
        this.AcceptedServerId = "";
        this.AccountBalance = GlobalConfigs.INIT_BALANCE;
        this.CommittedAccountBalance = GlobalConfigs.INIT_BALANCE;


        this.currentServerId = serverName;
        this.incomingTransactionsQueue = new PriorityBlockingQueue<>(100, new Comparator<TransactionInputConfig>() {
            @Override
            public int compare(TransactionInputConfig o1, TransactionInputConfig o2) {
                return o1.getTransaction().getTransactionNum() - o2.getTransaction().getTransactionNum();
            }
        });
        this.localTransactionLog = new LocalTransactionStore();
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

    public void updateDatabaseWithBlocks(List<BlockOfTransactions> blocks){
        for (BlockOfTransactions block : blocks){
            this.blocks.put(block.getTermNumber(), block);
            Main.node.logger.log("Block added: " + block.getTermNumber());
        }
    }

    public void commitBlock(int termNumber, BlockOfTransactions block, int balanceAfterTransactions){
        this.blocks.put(termNumber, block);
        this.CommittedAccountBalance = balanceAfterTransactions;
        this.CommittedProposalNumber = termNumber;
        this.transactionsCommitted += block.getTransactionsCount();
        Main.node.logger.log("Block committed: " + termNumber);
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
        return AccountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        AccountBalance = accountBalance;
    }

    public int getCommittedAccountBalance() {
        return CommittedAccountBalance;
    }

    public void setCommittedAccountBalance(int committedAccountBalance) {
        CommittedAccountBalance = committedAccountBalance;
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
}
