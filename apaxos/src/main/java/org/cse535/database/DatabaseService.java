package org.cse535.database;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.cse535.configs.GlobalConfigs;
import org.cse535.proto.BlockOfTransactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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













    public DatabaseService(){
        this.blocks = new HashMap<>();
        this.AcceptedBlockOfTransactions = null;
        this.AcceptedproposalNumber = 0;
        this.AcceptedServerId = null;
        this.AccountBalance = GlobalConfigs.INIT_BALANCE;
        this.CommittedAccountBalance = GlobalConfigs.INIT_BALANCE;
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
        }
    }

    public void commitBlock(int termNumber, BlockOfTransactions block, int balanceAfterTransactions){
        this.blocks.put(termNumber, block);
        this.CommittedAccountBalance = balanceAfterTransactions;
        this.CommittedProposalNumber = termNumber;

        this.transactionsCommitted += block.getTransactionsCount();

    }

    public void uncommitBlock(int termNumber){

        this.transactionsCommitted -= blocks.get(termNumber).getTransactionsCount();
        this.transactionsAborted += blocks.get(termNumber).getTransactionsCount();



        this.blocks.remove(termNumber);
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


}
