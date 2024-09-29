package org.cse535.database;

import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.Transaction;

import java.util.ArrayList;
import java.util.List;


public class LocalTransactionStoreService {

    public static List<Transaction> localTransactions = new ArrayList<>();

    public static int clientBalance = 1000;

    public static boolean addTransaction(Transaction transaction) {

        if(clientBalance > transaction.getAmount()) {
            clientBalance -= transaction.getAmount();
            localTransactions.add(transaction);
            return true;
        }
        return false;
    }

    public static List<Transaction> getTransactions() {
        return localTransactions;
    }

    public static void clearTransactions() {
        localTransactions.clear();
    }

    public static void removeTransaction(Transaction transaction) {
        localTransactions.remove(transaction);
    }

    public static void removeTransaction(int index) {
        localTransactions.remove(index);
    }

    public static BlockOfTransactions convertToBlock() {

        return BlockOfTransactions.newBuilder()
                .addAllTransactions(localTransactions)
                .setBlockHash(hashTransactions())
                .build();
    }

    private static String hashTransactions() {
        return "hash";
    }

}
