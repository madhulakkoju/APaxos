package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.*;

public class ActivateServersService extends ActivateServersGrpc.ActivateServersImplBase {

    @Override
    public void activateServer(org.cse535.proto.ActivateServerRequest request,
                               io.grpc.stub.StreamObserver<org.cse535.proto.ActivateServerResponse> responseObserver) {
        Main.node.isServerActive = true;
        responseObserver.onNext(org.cse535.proto.ActivateServerResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deactivateServer(DeactivateServerRequest request, StreamObserver<DeactivateServerResponse> responseObserver) {
        Main.node.isServerActive = false;
        responseObserver.onNext(DeactivateServerResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }


}
