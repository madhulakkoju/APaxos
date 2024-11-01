package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.node.ViewServer;
import org.cse535.proto.*;

public class LinearPBFTService extends LinearPBFTGrpc.LinearPBFTImplBase {

    @Override
    public void request(TransactionInputConfig request, StreamObserver<TxnResponse> responseObserver) {

        if(!Main.node.isServerActive()){
            return;
        }

        if( Main.node.isLeader() ){
            Main.node.database.incomingTnxQueue.add(request);

            TxnResponse response = TxnResponse.newBuilder().setSuccess(true).setServerName(Main.node.serverName).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else{
            TxnResponse response = TxnResponse.newBuilder().setSuccess(false).setServerName(Main.node.serverName).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void prePrepare(PrePrepareRequest request, StreamObserver<PrePrepareResponse> responseObserver) {

        if(!Main.node.isServerActive()){
            return;
        }

        PrePrepareResponse resp = Main.node.handlePrePrepare(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }


    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {

        if(!Main.node.isServerActive()){
            return;
        }

        PrepareResponse resp = Main.node.handlePrepare(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();

    }


    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {

        if(!Main.node.isServerActive()){
            return;
        }


        CommitResponse resp = Main.node.handleCommit(request);
        responseObserver.onNext(resp);
        responseObserver.onCompleted();

        Main.node.database.initiateExecutions();
    }

    @Override
    public void executionReply(ExecutionReplyRequest request, StreamObserver<ExecutionReplyResponse> responseObserver) {

        ViewServer.viewServerInstance.transactionExecutionResponseMap.get(request.getSequenceNumber()).add(request.getProcessId());

    }

    @Override
    public void viewChange(ViewChangeRequest request, StreamObserver<ViewChangeResponse> responseObserver) {

    }

    @Override
    public void newView(NewViewRequest request, StreamObserver<NewViewResponse> responseObserver) {

    }
}
