package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.*;

public class LinearPBFTService extends LinearPBFTGrpc.LinearPBFTImplBase {

    @Override
    public void request(TransactionInputConfig request, StreamObserver<TxnResponse> responseObserver) {

        if(Main.node.isServerActive() && Main.node.isLeader() ){
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

    }


    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {

    }


    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {

    }

    @Override
    public void executionReply(ExecutionReplyRequest request, StreamObserver<ExecutionReplyResponse> responseObserver) {

    }

    @Override
    public void viewChange(ViewChangeRequest request, StreamObserver<ViewChangeResponse> responseObserver) {

    }

    @Override
    public void newView(NewViewRequest request, StreamObserver<NewViewResponse> responseObserver) {

    }
}
