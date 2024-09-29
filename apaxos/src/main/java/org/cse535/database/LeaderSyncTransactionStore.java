package org.cse535.database;

import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LeaderSyncTransactionStore {

    public static List<Transaction> localTransactions = new ArrayList<>();

    public static void setLocalTransactions(List<Transaction> localTransactions) {
        LeaderSyncTransactionStore.localTransactions.addAll(localTransactions);
    }

    public static void addTransactions(List<Transaction> localTransactions) {
        LeaderSyncTransactionStore.localTransactions.addAll(localTransactions);
    }


    public static List<Transaction> getTransactions() {
        return localTransactions;
    }

    public static void clearTransactions() {
        localTransactions.clear();
    }


    public static BlockOfTransactions convertToBlock() {

        return BlockOfTransactions.newBuilder()
                .addAllTransactions(localTransactions)
                .setBlockHash(hashTransactions())
                .build();
    }

    public static String hashTransactions() {
        return "hash";
    }


}
