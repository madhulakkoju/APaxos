package org.cse535.database;

import com.google.protobuf.Timestamp;
import lombok.Data;
import org.cse535.Main;
import org.cse535.configs.GlobalConfigs;
import org.cse535.proto.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class DatabaseService {

    public enum TransactionStatus{
        None,
        REQUESTED,
        PrePREPARED,
        PREPARED,
        COMMITTED,
        EXECUTED,
        ABORTED,
        PENDING
    }

    // SeqNum : TransactionStatus
    public HashMap<Integer, TransactionStatus> transactionStatusMap = new HashMap<>();

    // SeqNum : Transaction
    public HashMap<Integer, Transaction> transactionMap = new HashMap<>();

    public HashMap<Integer, Integer> seqNumViewMap = new HashMap<>();


    // SeqNum : PrePrepareResponse
    public HashMap<Integer, List<PrePrepareResponse>> prePrepareResponseMap = new HashMap<>();

    // SeqNum : PrepareResponse
    public HashMap<Integer, List<PrepareResponse>> prepareResponseMap = new HashMap<>();

    public HashMap<String, Integer> accountsMap = new HashMap<>();

    public AtomicInteger currentSeqNum = new AtomicInteger(0);
    public AtomicInteger currentViewNum = new AtomicInteger(0);


    public PriorityBlockingQueue<TransactionInputConfig> incomingTnxQueue = new PriorityBlockingQueue<>();

    public AtomicBoolean isLeader = new AtomicBoolean(false);
    public AtomicBoolean isServerActive = new AtomicBoolean(false);



    AtomicInteger lastExecutedSeqNum = new AtomicInteger(-1);
    AtomicInteger maxAddedSeqNum = new AtomicInteger(-1);








    public DatabaseService(String serverName){

        for (String client : GlobalConfigs.clients) {
            accountsMap.put(client, GlobalConfigs.INIT_BALANCE);
        }





    }


    public void initiateExecutions(){

        while( lastExecutedSeqNum.get() < maxAddedSeqNum.get() ){

            int seqNum = lastExecutedSeqNum.get() + 1;

            if( transactionStatusMap.containsKey(seqNum) &&
                    transactionStatusMap.get(seqNum) == TransactionStatus.COMMITTED ){
                // Execute the transaction
                executeTransaction(seqNum);
                lastExecutedSeqNum.set(seqNum);
            }
            else{
                break;
            }
        }
    }

    public void executeTransaction( int seqNum ){
        Transaction transaction = transactionMap.get(seqNum);

        // Execute the transaction
        this.accountsMap.put(transaction.getSender(), this.accountsMap.get(transaction.getSender()) - transaction.getAmount() );
        this.accountsMap.put(transaction.getReceiver(), this.accountsMap.get(transaction.getReceiver()) + transaction.getAmount() );

        this.transactionStatusMap.put(seqNum, TransactionStatus.EXECUTED);

        Main.node.clientStub.executionReply(
                ExecutionReplyRequest.newBuilder()
                        .setView(this.seqNumViewMap.get(seqNum))
                        .setSequenceNumber(seqNum)
                        .setProcessId(Main.node.serverName)
                        .build()
        );

    }










    public void setLastExecutedSeqNum(int seqNum){
        lastExecutedSeqNum.set(seqNum);
    }

    public void setMaxAddedSeqNum(int seqNum){
        maxAddedSeqNum.set( Math.max( seqNum , maxAddedSeqNum.get() ) );
    }




}
