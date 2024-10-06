package org.cse535.threadimpls;

import org.cse535.node.Node;
import org.cse535.proto.*;

public class CommitWorkerThread extends Thread{

    private final Node node;
    private final int targetPort;
    private final String targetServerName;

    private final CommitRequest commitRequest;

    public CommitWorkerThread(Node node, int targetPort, String targetServerName,
                              CommitRequest commitRequest) {

        this.node = node;
        this.targetPort = targetPort;
        this.targetServerName = targetServerName;

        this.commitRequest = commitRequest;
    }

    public void run() {
        if(this.targetPort == this.node.port){
            return;
        }
        ApaxosGrpc.ApaxosBlockingStub blockingStub = ApaxosGrpc.newBlockingStub(
                this.node.serversToChannel.get(this.targetServerName)
        );

        CommitResponse resp = blockingStub.commit(this.commitRequest);
        this.node.commitResponseMap.put(this.targetServerName, resp);
    }
}
