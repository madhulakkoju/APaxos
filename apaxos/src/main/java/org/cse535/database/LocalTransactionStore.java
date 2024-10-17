package org.cse535.database;

import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalTransactionStore {

    public List<Transaction> localTransactions;


    public LocalTransactionStore(){
        localTransactions = Collections.synchronizedList(new ArrayList<>());
    }

    public LocalTransactionStore(List<Transaction> tnxs){
        localTransactions = Collections.synchronizedList(new ArrayList<>());
        localTransactions.addAll(tnxs);
    }


    public void addTransaction(Transaction transaction){
        localTransactions.add(transaction);
    }


    public void removeTransaction(Transaction transaction){
        localTransactions.remove(transaction);
    }


    public List<Transaction> getAllTransactions(){
        return localTransactions;
    }


    public synchronized void clearTransactions() {
        localTransactions.clear();
    }
}
