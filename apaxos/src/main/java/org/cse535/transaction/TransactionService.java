package org.cse535.transaction;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.database.LocalTransactionStoreService;
import org.cse535.proto.SyncRequest;
import org.cse535.proto.SyncResponse;
import org.cse535.proto.Transaction;
import org.cse535.proto.TransactionServiceGrpc;

public class TransactionService extends TransactionServiceGrpc.TransactionServiceImplBase {


    @Override
    public void addTransaction(Transaction request, StreamObserver<Transaction> responseObserver) {

        Main.node.receiveTransaction(request);


        boolean isValidTransaction = LocalTransactionStoreService.addTransaction(request);

        if(isValidTransaction) {
            responseObserver.onNext(request);
        } else {




        }
    }


    // To send over all the transactions that the server currently has in Local Transaction Store
    @Override
    public void synchronize(SyncRequest request, StreamObserver<SyncResponse> responseObserver) {

        SyncResponse.Builder response = SyncResponse.newBuilder();

        response.setId(request.getSyncID());

        LocalTransactionStoreService.getTransactions().forEach(response::addTransactions);

        responseObserver.onNext(response.build());

    }


    




}
