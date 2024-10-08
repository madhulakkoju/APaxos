package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.TnxPropagateGrpc;
import org.cse535.proto.TransactionInputConfig;
import org.cse535.proto.TxnResponse;

public class TransactionPropagateService extends TnxPropagateGrpc.TnxPropagateImplBase {

    @Override
    public void propagateTransaction(TransactionInputConfig request, StreamObserver<TxnResponse> responseObserver) {
        Main.commonLogger.log("Received transaction " + request.getTransaction().getTransactionNum() + " "
                + request.getTransaction().getSender() + " -> "
                + request.getTransaction().getReceiver() + " = "
                + request.getTransaction().getAmount());

        Main.node.addTransactionToQueue(request);
        Main.commonLogger.log("Transaction added to queue");

        TxnResponse response = TxnResponse.newBuilder().setSuccess(true).setServerName(Main.node.serverName).build();
        Main.commonLogger.log("Transaction propagated successfully");

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        Main.commonLogger.log("Transaction response sent back to client");
    }
}
