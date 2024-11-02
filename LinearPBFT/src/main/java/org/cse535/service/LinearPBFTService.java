package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.node.ViewServer;
import org.cse535.proto.*;

import java.util.HashSet;

public class LinearPBFTService extends LinearPBFTGrpc.LinearPBFTImplBase {

    @Override
    public void request(TransactionInputConfig request, StreamObserver<TxnResponse> responseObserver) {

        Main.node.logger.log("Request received: " + request.getTransaction().getTransactionNum());

        if(!Main.node.isServerActive()){
            Main.node.logger.log("Server is not active -> left request");

            return;
        }

        if( Main.node.isLeader() ){

            Main.node.logger.log("Server is leader -> processing request -- added to queue");
            Main.node.database.incomingTnxQueue.add(request);

            TxnResponse response = TxnResponse.newBuilder().setSuccess(true).setServerName(Main.node.serverName).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else{
            Main.node.logger.log("Server is not leader -> left request");
            TxnResponse response = TxnResponse.newBuilder().setSuccess(false).setServerName(Main.node.serverName).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void prePrepare(PrePrepareRequest request, StreamObserver<PrePrepareResponse> responseObserver) {

        Main.node.logger.log("PrePrepare request received: " + request.getSequenceNumber());
        if(!Main.node.isServerActive()){
            Main.node.logger.log("Server is not active -> left request");
            return;
        }

        PrePrepareResponse resp = Main.node.handlePrePrepare(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
        Main.node.logger.log("PrePrepare response sent: " + resp.getSequenceNumber());
    }


    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {

        Main.node.logger.log("Prepare request received: " + request.getSequenceNumber());
        if(!Main.node.isServerActive()){
            Main.node.logger.log("Server is not active -> left request");
            return;
        }

//        //Byzantine Feature -> does not prepare at all
//        if(Main.node.isServerByzantine()){
//           return;
//        }

        PrepareResponse resp = Main.node.handlePrepare(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();

        Main.node.logger.log("Prepare response sent: " + resp.getSequenceNumber());
    }


    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {

        Main.node.logger.log("Commit request received: " + request.getSequenceNumber());
        if(!Main.node.isServerActive()){
            return;
        }

        CommitResponse resp = Main.node.handleCommit(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();

        Main.node.logger.log("Commit response sent: " + resp.getSequenceNumber());

        Main.node.database.initiateExecutions();

        Main.node.logger.log("Executions initiated");
    }

    @Override
    public void executionReply(ExecutionReplyRequest request, StreamObserver<ExecutionReplyResponse> responseObserver) {

        responseObserver.onNext(ExecutionReplyResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();

        ViewServer.viewServerInstance.logger.log("Execution reply received: " + request.getSequenceNumber() );

        if(!ViewServer.viewServerInstance.transactionExecutionResponseMap.containsKey( request.getSequenceNumber() )){
            ViewServer.viewServerInstance.transactionExecutionResponseMap.put(request.getSequenceNumber(), new HashSet<>());
        }

        ViewServer.viewServerInstance.transactionExecutionResponseMap.get(request.getSequenceNumber()).add(request.getProcessId());

    }

    @Override
    public void viewChange(ViewChangeRequest request, StreamObserver<ViewChangeResponse> responseObserver) {

    }

    @Override
    public void newView(NewViewRequest request, StreamObserver<NewViewResponse> responseObserver) {

    }
}
