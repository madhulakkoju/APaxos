package org.cse535.database;

import lombok.Data;
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
        ABORTED,
        PENDING
    }

    // SeqNum : TransactionStatus
    public HashMap<Integer, TransactionStatus> transactionStatusMap = new HashMap<>();

    // SeqNum : Transaction
    public HashMap<Integer, Transaction> transactionMap = new HashMap<>();

    // SeqNum : PrePrepareResponse
    public HashMap<Integer, List<PrePrepareResponse>> prePrepareResponseMap = new HashMap<>();

    // SeqNum : PrepareResponse
    public HashMap<Integer, List<PrepareResponse>> prepareResponseMap = new HashMap<>();





    public AtomicInteger currentSeqNum = new AtomicInteger(0);
    public AtomicInteger currentViewNum = new AtomicInteger(0);


    public PriorityBlockingQueue<TransactionInputConfig> incomingTnxQueue = new PriorityBlockingQueue<>();

    public AtomicBoolean isLeader = new AtomicBoolean(false);
    public AtomicBoolean isServerActive = new AtomicBoolean(false);













    public DatabaseService(String serverName){


    }

//    public DatabaseSnapshot toSnapshot(){
//        return DatabaseSnapshot.newBuilder()
//                .setAcceptedProposalNumber(this.getAcceptedproposalNumber())
//                .setAcceptedServerId(this.getAcceptedServerId())
//                .setAcceptedBlockOfTransactions(this.getAcceptedBlockOfTransactions())
//                .setAccountBalance(this.getAccountBalance())
//                .setCommittedAccountBalance(this.getCommittedAccountBalance())
//                .setCommittedProposalNumber(this.getCommittedProposalNumber())
//                .putAllBlocks(this.getBlocks())
//                .putAllBalanceAfterCommit(this.getBalanceAfterCommit())
//                .putAllClientBalancesAfterCommit(this.getClientBalancesAfterCommit())
//                .setTransactionsProcessed(this.getTransactionsProcessed())
//                .setTransactionsCommitted(this.getTransactionsCommitted())
//                .setTransactionsAborted(this.getTransactionsAborted())
//                .setCurrentProposalNumber(this.getCurrentProposalNumber())
//                .setCurrentServerId(this.getCurrentServerId())
//                .addAllIncomingTransactionsQueue(this.getIncomingTransactionsQueue())
//                .addAllLocalTransactionLog(this.getLocalTransactionLog().getAllTransactions())
//                .addAllLeaderTransactionLog(this.getLeaderTransactionLog().getAllTransactions())
//                .setLastPrepareAckTimestamp(this.getLastPrepareAckTimestamp())
//                .setLastPrepareAckServer(this.getLastPrepareAckServer())
//                .setLastAcceptAckTimestamp(this.getLastAcceptAckTimestamp())
//                .setLastCommitTimestamp(this.getLastCommitTimestamp())
//                .addAllProcessedTransactionsSet(this.getProcessedTransactionsSet())
//                .putAllTimeTakenToExecuteMap(Utils.toTimeTakenMask( this.getTimeTakenToExecuteMap()))
//                .setLeaderBalanceAfterTransactions(this.getLeaderTransactionLog().BalanceAfterTransactions)
//                .build();
//    }
//
//    public static DatabaseService toDatabaseService(DatabaseSnapshot snapshot){
//
//
//        PriorityBlockingQueue<TransactionInputConfig> q = new PriorityBlockingQueue<>(100, new Comparator<TransactionInputConfig>() {
//            @Override
//            public int compare(TransactionInputConfig o1, TransactionInputConfig o2) {
//                return o1.getTransaction().getTransactionNum() - o2.getTransaction().getTransactionNum();
//            }
//        } );
//
//        q.addAll(snapshot.getIncomingTransactionsQueueList());
//
//
//        DatabaseService databaseService = new DatabaseService(snapshot.getCurrentServerId());
//        databaseService.setAcceptedproposalNumber(snapshot.getAcceptedProposalNumber());
//        databaseService.setAcceptedServerId(snapshot.getAcceptedServerId());
//        databaseService.setAcceptedBlockOfTransactions(snapshot.getAcceptedBlockOfTransactions());
//        databaseService.setAccountBalance(snapshot.getAccountBalance());
//        databaseService.setCommittedAccountBalance(snapshot.getCommittedAccountBalance());
//        databaseService.setCommittedProposalNumber(snapshot.getCommittedProposalNumber());
//        databaseService.setBlocks(new HashMap<>(snapshot.getBlocksMap()));
//        databaseService.setBalanceAfterCommit(new HashMap<>(snapshot.getBalanceAfterCommitMap()));
//        databaseService.setClientBalancesAfterCommit(new HashMap<>(snapshot.getClientBalancesAfterCommitMap()));
//        databaseService.setTransactionsProcessed(snapshot.getTransactionsProcessed());
//        databaseService.setTransactionsCommitted(snapshot.getTransactionsCommitted());
//        databaseService.setTransactionsAborted(snapshot.getTransactionsAborted());
//        databaseService.setCurrentProposalNumber(snapshot.getCurrentProposalNumber());
//        databaseService.setCurrentServerId(snapshot.getCurrentServerId());
//        databaseService.setIncomingTransactionsQueue( q );
//        databaseService.setLocalTransactionLog(new LocalTransactionStore(snapshot.getLocalTransactionLogList()));
//        databaseService.setLeaderTransactionLog(new LeaderLocalTnxStore(Main.node, snapshot.getLeaderTransactionLogList(), snapshot.getLeaderBalanceAfterTransactions()));
//        databaseService.setLastPrepareAckTimestamp(snapshot.getLastPrepareAckTimestamp());
//        databaseService.setLastPrepareAckServer(snapshot.getLastPrepareAckServer());
//        databaseService.setLastAcceptAckTimestamp(snapshot.getLastAcceptAckTimestamp());
//        databaseService.setLastCommitTimestamp(snapshot.getLastCommitTimestamp());
//        databaseService.setProcessedTransactionsSet(new HashSet<>(snapshot.getProcessedTransactionsSetList()));
//        databaseService.setTimeTakenToExecuteMap(Utils.toTimeTakenMap(snapshot.getTimeTakenToExecuteMapMap()));
//
//        return databaseService;
//
//    }
//







}
