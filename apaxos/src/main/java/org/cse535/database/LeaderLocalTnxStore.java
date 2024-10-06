package org.cse535.database;

import org.cse535.configs.GlobalConfigs;
import org.cse535.node.Node;
import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LeaderLocalTnxStore {

    public List<Transaction> transactions;

    public int BalanceAfterTransactions;

    public Node node;

    public LeaderLocalTnxStore(Node node) {
        this.transactions = new ArrayList<>();
        this.BalanceAfterTransactions = GlobalConfigs.INIT_BALANCE;
        this.node = node;
    }

    public void computeBalanceAfterTransactions(){

        BalanceAfterTransactions = this.node.database.getCommittedAccountBalance();

        for(Transaction transaction : transactions){

            if(transaction.getSender().equals( node.serverName))
                BalanceAfterTransactions -= transaction.getAmount();

            else if(transaction.getReceiver().equals( node.serverName))
                BalanceAfterTransactions += transaction.getAmount();

        }
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void addAllTransactions(List<Transaction> transactions){
        this.transactions.addAll(transactions);
    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    public BlockOfTransactions convertToBlockOfTransactions(int termNumber){
        return BlockOfTransactions.newBuilder()
                .addAllTransactions(transactions)
                .setTermNumber(termNumber)
                .build();
    }

    public void clearTransactions(){
        transactions.clear();
    }

    public void reorderTransactionsBasedOnTimestamp(){
        transactions.sort((tnx1, tnx2) -> {

            if(tnx1.getTimestamp().getSeconds() == tnx2.getTimestamp().getSeconds())
                return tnx1.getTimestamp().getNanos() - tnx2.getTimestamp().getNanos();

            return Math.toIntExact(tnx1.getTimestamp().getSeconds() - tnx2.getTimestamp().getSeconds());

        });
    }

}
