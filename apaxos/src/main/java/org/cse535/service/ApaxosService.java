package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.*;

public class ApaxosService extends ApaxosGrpc.ApaxosImplBase {

    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        responseObserver.onNext(Main.node.handlePreparePhase(request));
        responseObserver.onCompleted();
    }

    @Override
    public void accept(AcceptRequest request, StreamObserver<AcceptResponse> responseObserver) {
        responseObserver.onNext(Main.node.handleAcceptPhase(request));
        responseObserver.onCompleted();
    }

    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {
        responseObserver.onNext(Main.node.handleCommitPhase(request));
        responseObserver.onCompleted();
    }

}
