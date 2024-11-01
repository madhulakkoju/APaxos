package org.cse535.node;


import com.google.protobuf.InvalidProtocolBufferException;
import org.cse535.configs.GlobalConfigs;
import org.cse535.configs.Utils;
import org.cse535.database.DatabaseService;
import org.cse535.proto.*;
import org.cse535.threadimpls.CommitWorkerThread;
import org.cse535.threadimpls.DatabaseBackupThread;
import org.cse535.threadimpls.PrePrepareWorkerThread;
import org.cse535.threadimpls.PrepareWorkerThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Node extends NodeServer{

    public Thread transactionWorkerThread;
    //public DatabaseBackupThread databaseBackupThread;




    public Node(String serverName, int port){
        super(serverName, port);

        this.transactionWorkerThread = new Thread(this::processTnxsInQueue);
        //this.databaseBackupThread = new DatabaseBackupThread(this);


        try {
            this.server.start();
            this.transactionWorkerThread.start();
            //this.databaseBackupThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processTnxsInQueue() {
        logger.log("Transaction Worker Thread Started");

        TransactionInputConfig tnxConfig = null;

        while (true) {
            try {
                Thread.sleep(5);

                if(this.database.incomingTnxQueue.isEmpty()){

                    Thread.sleep(50);

                    continue;
                }


                tnxConfig = this.database.incomingTnxQueue.poll();



                if(!processTransaction(tnxConfig)){
                    this.database.incomingTnxQueue.add(tnxConfig);
                }


            } catch (InterruptedException e) {
                this.commandLogger.log("Line 143 ::: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }



    public boolean processTransaction(TransactionInputConfig tnxConfig) {

        this.currentActiveServers = tnxConfig.getServerNamesList();

        int currentSeqNum = this.database.currentSeqNum.incrementAndGet();

        this.database.transactionMap.put(currentSeqNum, tnxConfig.getTransaction());
        this.database.transactionStatusMap.put(currentSeqNum, DatabaseService.TransactionStatus.REQUESTED);

        PrePrepareRequest prePrepareRequest = PrePrepareRequest.newBuilder()
                .setTransaction(tnxConfig.getTransaction())
                .setSequenceNumber(currentSeqNum)
                .setView(this.database.currentViewNum.get())
                .setProcessId(this.serverName)
                .setDigest(Utils.Digest(tnxConfig.getTransaction()))
                .build();

        this.database.prePrepareResponseMap.put(currentSeqNum, new ArrayList<>(GlobalConfigs.serversCount));

        // Initiate PrePrepare
        if( initiatePrePrepare(prePrepareRequest) ){

            PrepareRequest prepareRequest = PrepareRequest.newBuilder()
                    .setSequenceNumber(currentSeqNum)
                    .setView(this.database.currentViewNum.get())
                    .setProcessId(this.serverName)
                    //.setDigest(Utils.Digest(tnxConfig.getTransaction()))
                    .setDigest(tnxConfig.getTransaction().getTransactionHash())
                    .build();

            this.database.prepareResponseMap.put(currentSeqNum, new ArrayList<>(GlobalConfigs.serversCount));

            // Initiate Prepare
            if( initiatePrepare(prepareRequest) ){

                CommitRequest commitRequest = CommitRequest.newBuilder()
                        .setSequenceNumber(currentSeqNum)
                        .setView(this.database.currentViewNum.get())
                        .setProcessId(this.serverName)
                        .setDigest(Utils.Digest(tnxConfig.getTransaction()))
                        .build();


                // Initiate Commit
                initiateCommit(commitRequest);

                return true;

            }
        }
        return false;
    }



    public boolean initiatePrePrepare(PrePrepareRequest preprepareRequest) {
        try {

            PrePrepareWorkerThread[] prePrepareWorkerThreads = new PrePrepareWorkerThread[this.currentActiveServers.size()];

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                String serverName = this.currentActiveServers.get(i);
                int port = GlobalConfigs.serversToPortMap.get(serverName);
                prePrepareWorkerThreads[i] = new PrePrepareWorkerThread(this, port, serverName, preprepareRequest);
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                prePrepareWorkerThreads[i].start();
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                    prePrepareWorkerThreads[i].join();
            }

            int prePrepareAcceptedCount = 0 ; // this.database.prePrepareResponseMap.get(preprepareRequest.getSequenceNumber()).size();

            for (PrePrepareResponse resp : this.database.prePrepareResponseMap.get(preprepareRequest.getSequenceNumber()) ) {
                if(resp.getSuccess()){
                    prePrepareAcceptedCount++;
                }
            }

            if(prePrepareAcceptedCount >= GlobalConfigs.minQuoromSize){
                this.database.transactionStatusMap.put(preprepareRequest.getSequenceNumber(), DatabaseService.TransactionStatus.PrePREPARED);
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public PrePrepareResponse handlePrePrepare(PrePrepareRequest request) {
        PrePrepareResponse.Builder prePrepareResponse = PrePrepareResponse.newBuilder();

        prePrepareResponse.setSuccess(false);

        if(this.database.transactionMap.containsKey(request.getSequenceNumber())){

            if( this.database.transactionMap.get(request.getSequenceNumber()).getTransactionNum() != request.getTransaction().getTransactionNum() &&
                !this.database.transactionMap.get(request.getSequenceNumber()).getTransactionHash().equals(request.getTransaction().getTransactionHash())){

                prePrepareResponse.setSuccess(false);
                return prePrepareResponse.build();
            }

            this.database.transactionStatusMap.put(request.getSequenceNumber(), DatabaseService.TransactionStatus.PrePREPARED);
            prePrepareResponse.setSuccess(true);

        }
        else {
            this.database.transactionMap.put(request.getSequenceNumber(), request.getTransaction());
            this.database.transactionStatusMap.put(request.getSequenceNumber(), DatabaseService.TransactionStatus.PrePREPARED);
            prePrepareResponse.setSuccess(true);
        }

        return prePrepareResponse.build();
    }



    public boolean initiatePrepare(PrepareRequest prepareRequest) {
        try {

            PrepareWorkerThread[] prepareWorkerThreads = new PrepareWorkerThread[this.currentActiveServers.size()];

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                String serverName = this.currentActiveServers.get(i);
                int port = GlobalConfigs.serversToPortMap.get(serverName);
                prepareWorkerThreads[i] = new PrepareWorkerThread(this, port, serverName, prepareRequest);
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                prepareWorkerThreads[i].start();
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                prepareWorkerThreads[i].join();
            }

            int prepareAcceptedCount = 0 ; // this.database.prePrepareResponseMap.get(preprepareRequest.getSequenceNumber()).size();

            for (PrePrepareResponse resp : this.database.prePrepareResponseMap.get(prepareRequest.getSequenceNumber()) ) {
                if(resp.getSuccess()){
                    prepareAcceptedCount++;
                }
            }

            if(prepareAcceptedCount >= GlobalConfigs.minQuoromSize){
                this.database.transactionStatusMap.put(prepareRequest.getSequenceNumber(), DatabaseService.TransactionStatus.PREPARED );
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public PrepareResponse handlePrepare(PrepareRequest request) {
        PrepareResponse.Builder prepareResponse = PrepareResponse.newBuilder();
        prepareResponse.setSuccess(false);

        if( this.database.transactionMap.containsKey(request.getSequenceNumber()) &&
                this.database.transactionStatusMap.containsKey(request.getSequenceNumber()) &&
                this.database.transactionMap.get(request.getSequenceNumber()).getTransactionHash().equals(request.getDigest()) &&
                (this.database.transactionStatusMap.get(request.getSequenceNumber()) == DatabaseService.TransactionStatus.PrePREPARED ||
                this.database.transactionStatusMap.get(request.getSequenceNumber()) == DatabaseService.TransactionStatus.PREPARED)){

            this.database.transactionStatusMap.put(request.getSequenceNumber(), DatabaseService.TransactionStatus.PREPARED);
            prepareResponse.setSuccess(true);
        }

        return prepareResponse.build();
    }



    public boolean initiateCommit(CommitRequest commitRequest) {
        try {

            this.database.transactionStatusMap.put(commitRequest.getSequenceNumber(), DatabaseService.TransactionStatus.COMMITTED );

            CommitWorkerThread[] commitWorkerThreads = new CommitWorkerThread[this.currentActiveServers.size()];

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                String serverName = this.currentActiveServers.get(i);
                int port = GlobalConfigs.serversToPortMap.get(serverName);
                commitWorkerThreads[i] = new CommitWorkerThread(this, port, serverName, commitRequest);
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                commitWorkerThreads[i].start();
            }

            for (int i = 0; i < this.currentActiveServers.size(); i++) {
                commitWorkerThreads[i].join();
            }

            return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    public CommitResponse handleCommit(CommitRequest request) {
        this.database.transactionStatusMap.put(request.getSequenceNumber(), DatabaseService.TransactionStatus.COMMITTED);
        return CommitResponse.newBuilder().setSuccess(true).build();
    }








    public boolean isLeader(){
            return this.database.isLeader.get();
    }

    public boolean isServerActive() {
        return this.database.isServerActive.get();
    }


}
