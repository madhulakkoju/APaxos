package org.cse535.configs;

import org.cse535.database.TimeTakenToExecute;
import org.cse535.proto.BlockOfTransactions;
import org.cse535.proto.TimeTakenMask;
import org.cse535.proto.Transaction;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String toString(Transaction transaction) {
        return "Transaction ( "+ transaction.getTransactionNum() + " : " + transaction.getSender() + " -> " + transaction.getReceiver() + " = " + transaction.getAmount() + " )";
    }

    public static HashMap<Integer, TimeTakenToExecute> toTimeTakenMap(Map<Integer, TimeTakenMask> timeTakenToExecuteMapMap) {
        HashMap<Integer, TimeTakenToExecute> timeTakenToExecuteMap = new HashMap<>();
        timeTakenToExecuteMapMap.forEach( (term, time) -> {
            TimeTakenToExecute timeTakenToExecute = new TimeTakenToExecute();
            timeTakenToExecute.startTime = time.getStartTime();
            timeTakenToExecute.endTime = time.getEndTime();
            timeTakenToExecuteMap.put(term, timeTakenToExecute);
        });
        return timeTakenToExecuteMap;
    }

    public String toString(Transaction[] transactions) {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions) {
            sb.append(toString(transaction)).append("\n");
        }
        return sb.toString();
    }

    public static String toString(BlockOfTransactions block){
        StringBuilder sb = new StringBuilder();
        sb.append("Block: " + block.getBlockNum() + " Term:" + block.getTermNumber()
                + " Tnx count: "+ block.getTransactionsCount()+ " Leader: " + block.getLeader()+
                " Block Commit Time: " + block.getBlockCommitTime() + "\n");
        for (Transaction transaction : block.getTransactionsList()) {
            sb.append(toString(transaction)).append("\n");
        }
        return sb.toString();
    }








    public static String toString(BlockOfTransactions[] blocks){
        StringBuilder sb = new StringBuilder();
        for (BlockOfTransactions block : blocks) {
            sb.append(toString(block)).append("\n");
        }
        return sb.toString();
    }

    public static String toString(HashMap<Integer, BlockOfTransactions> blocks){
        StringBuilder sb = new StringBuilder();
        blocks.forEach( (term,block) -> sb.append("Term : ").append(term).append("\n").append(toString(block)) );
        return sb.toString();
    }

    public static HashMap<Integer, TimeTakenMask> toTimeTakenMask(HashMap<Integer, TimeTakenToExecute> timeTaken){
        HashMap<Integer, TimeTakenMask> timeTakenMask = new HashMap<>();
        timeTaken.forEach( (term, time) -> timeTakenMask.put( term,
                TimeTakenMask.newBuilder().setStartTime(time.startTime).setEndTime(time.endTime).build()));
        return timeTakenMask;
    }



}
