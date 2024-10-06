package org.cse535.threadimpls;

import org.cse535.node.Node;
import org.cse535.proto.AcceptRequest;
import org.cse535.proto.AcceptResponse;
import org.cse535.proto.ApaxosGrpc;

public class AcceptWorkerThread extends Thread {

    private final Node node;
    private final int targetPort;
    private final String targetServerName;

    private final AcceptRequest acceptRequest;

    public AcceptWorkerThread(Node node, int targetPort, String targetServerName, AcceptRequest acceptRequest) {
        this.node = node;
        this.targetPort = targetPort;
        this.targetServerName = targetServerName;
        this.acceptRequest = acceptRequest;
    }

    public void run() {
        if(this.targetPort == this.node.port){
            return;
        }
        ApaxosGrpc.ApaxosBlockingStub blockingStub = ApaxosGrpc.newBlockingStub(
                this.node.serversToChannel.get(this.targetServerName)
        );
        AcceptResponse resp = blockingStub.accept(this.acceptRequest);
        this.node.acceptResponseMap.put(this.targetServerName, resp);

    }
}
