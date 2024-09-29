package org.cse535.node;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.cse535.database.LeaderSyncTransactionStore;
import org.cse535.database.LocalTransactionStoreService;
import org.cse535.loggers.LogUtils;
import org.cse535.proto.Transaction;
import org.cse535.threadimpls.BlockCastThread;
import org.cse535.threadimpls.SyncThread;
import org.cse535.transaction.TransactionService;

import java.io.IOException;
import java.util.HashMap;


public class Node {

    public String assignedClientID;
    public int port;
    public LogUtils logger;
    public String serverName;
    public Server server;

    public boolean isLeader = false;

    public Node(String assignedClientID, String serverName, int port) {
        this.assignedClientID = assignedClientID;
        this.port = port;
        this.serverName = serverName;

        try{

            server = ServerBuilder.forPort(port)
                    .addService(new TransactionService())
                    .build();

            this.logger = new LogUtils(port);

            server.start();

        }
        catch (RuntimeException | IOException e) {
            logger.log("Error occurred while starting the server: " + e.getMessage());
        }

    }





    public void receiveTransaction(Transaction transaction) {
        logger.log("Received transaction: " + transaction.toString());

        if( this.assignedClientID.equalsIgnoreCase(transaction.getSender()) ) {
            logger.log("Transaction is from the same client, considering it");

            boolean isValidTransaction = LocalTransactionStoreService.addTransaction(transaction);

            if (!isValidTransaction) {
                // Now, switch to leader mode

                isLeader = true;

                // Initiate Synchronize and request for all nodes to send their transactions


                initiateSync();



                LocalTransactionStoreService.addTransaction(transaction);
            }
        }
    }



    public void initiateSync() {
        // Send a request to all the nodes to send their transactions

        LeaderSyncTransactionStore.addTransactions(LocalTransactionStoreService.getTransactions());

        SyncThread[] threads = new SyncThread[NodePool.ports.length - 1];

        HashMap<Integer, Boolean> syncStatus = new HashMap<>();

        for (int i = 0; i < NodePool.ports.length; i++) {
            if (NodePool.ports[i] != this.port) {
                syncStatus.put(NodePool.ports[i], false);
                threads[i] = new SyncThread(NodePool.ports[i], this, syncStatus);
                threads[i].run();

            }
        }

        boolean allSynced = false;

        int synced = 0, notsynced = 0;

        boolean hasQuoromReached = false;

        while (!allSynced) {
            allSynced = true;
            for (int i = 0; i < threads.length; i++) {
                if(threads[i] != null && !threads[i].isAlive() ) {
                    allSynced = allSynced && syncStatus.get(NodePool.ports[i]);

                    if(syncStatus.get(NodePool.ports[i])) {
                        synced++;
                    }
                    else {
                        notsynced++;
                    }
                }
            }

            if( synced >= ( 1 + threads.length / 2 ) ){
                hasQuoromReached = true;
                break;
            }
        }

        if(hasQuoromReached) {
            // Commit the transactions
            LocalTransactionStoreService.clearTransactions();

            initiateBlockCast();

            LeaderSyncTransactionStore.clearTransactions();
        }
        else {
            // Rollback the transactions
            LocalTransactionStoreService.clearTransactions();
        }


    }


    public void initiateBlockCast() {
        // Send the block to all the nodes

        BlockCastThread[] threads = new BlockCastThread[NodePool.ports.length - 1];

        HashMap<Integer, Boolean> blockStatus = new HashMap<>();

        for (int i = 0; i < NodePool.ports.length; i++) {
            if (NodePool.ports[i] != this.port) {
                blockStatus.put(NodePool.ports[i], false);
                threads[i] = new BlockCastThread(NodePool.ports[i], this, blockStatus);
                threads[i].run();
            }
        }

        LeaderSyncTransactionStore.clearTransactions();

    }





}
