package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.node.DatabaseServer;
import org.cse535.proto.*;

public class DatabaseBackupService extends databaseGrpc.databaseImplBase {

    @Override
    public void get(DataGetRequest request, StreamObserver<DataGetResponse> responseObserver) {
        System.out.println("Received request for snapshot from " + request.getServerName());

        if(DatabaseServer.activeServer.databaseSnapshots.containsKey(request.getServerName())){
            responseObserver.onNext(
                    DataGetResponse.newBuilder()
                            .setSnapshot(DatabaseServer.activeServer.databaseSnapshots.get(request.getServerName()))
                            .setSuccess(true)
                            .build()
            );
        }
        else{
            responseObserver.onNext(
                    DataGetResponse.newBuilder()
                            .setSuccess(false)
                            .build()
            );
        }


        responseObserver.onCompleted();
    }

    @Override
    public void save(DataSaveRequest request, StreamObserver<DataSaveResponse> responseObserver) {
        responseObserver.onNext(
                DataSaveResponse.newBuilder()
                        .setSuccess(true)
                        .build()
        );
        responseObserver.onCompleted();

        System.out.println("Received request to save snapshot from " + request.getServerName());

        DatabaseServer.activeServer.logger.log("Received request to save snapshot from " + request.getServerName());
        DatabaseServer.activeServer.logger.log("Snapshot received: \n" + request.getSnapshot());

        DatabaseServer.activeServer.databaseSnapshots.put(
                request.getServerName(),
                request.getSnapshot()
        );
    }
}

