package org.cse535.node;

import io.grpc.stub.StreamObserver;
import org.cse535.proto.*;

public class ActivateServerService extends ActivateServersGrpc.ActivateServersImplBase {

    @Override
    public void activateServer(ActivateServerRequest request,
                         StreamObserver<ActivateServerResponse> responseObserver) {

        System.out.println("Server " + request.getServerName() + " is activated");

        ActivateServerResponse response = ActivateServerResponse.newBuilder()
                .setSuccess(true)
                .setServerName(request.getServerName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void deactivateServer(DeactivateServerRequest request,
                                 StreamObserver<DeactivateServerResponse> responseObserver) {

        System.out.println("Server " + request.getServerName() + " is activated");

        DeactivateServerResponse response = DeactivateServerResponse.newBuilder()
                .setSuccess(true)
                .setServerName(request.getServerName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
