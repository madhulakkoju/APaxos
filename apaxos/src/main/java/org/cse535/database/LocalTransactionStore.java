package org.cse535.database;

import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.Transaction;

import java.util.List;

public class LocalTransactionStore {

    public List<Transaction> localTransactions;

    public void addTransaction(Transaction transaction){
        localTransactions.add(transaction);
    }


    public void removeTransaction(Transaction transaction){
        localTransactions.remove(transaction);
    }


    public List<Transaction> getAllTransactions(){
        return localTransactions;
    }


}
