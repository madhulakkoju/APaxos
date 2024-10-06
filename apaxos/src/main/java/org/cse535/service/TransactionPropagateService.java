package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.TnxPropagateGrpc;
import org.cse535.proto.TransactionInputConfig;
import org.cse535.proto.TxnResponse;

public class TransactionPropagateService extends TnxPropagateGrpc.TnxPropagateImplBase {

    @Override
    public void propagateTransaction(TransactionInputConfig request, StreamObserver<TxnResponse> responseObserver) {
        Main.node.addTransactionToQueue(request);
        TxnResponse response = TxnResponse.newBuilder().setSuccess(true).setServerName(Main.node.serverName).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
