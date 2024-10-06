package org.cse535.service;

import org.cse535.Main;
import org.cse535.proto.ActivateServersGrpc;

public class ActivateServersService extends ActivateServersGrpc.ActivateServersImplBase {

    public void activateServer(org.cse535.proto.ActivateServerRequest request,
                               io.grpc.stub.StreamObserver<org.cse535.proto.ActivateServerResponse> responseObserver) {
        Main.node.isServerActive = true;
        responseObserver.onNext(org.cse535.proto.ActivateServerResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

}
