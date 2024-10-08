package org.cse535.threadimpls;

import io.grpc.ManagedChannel;
import lombok.var;
import org.cse535.Main;
import org.cse535.node.Node;
import org.cse535.proto.ApaxosGrpc;
import org.cse535.proto.PrepareRequest;
import org.cse535.proto.PrepareResponse;
import org.cse535.service.ApaxosService;

import static java.lang.System.in;

// PrepareWorkerThread  to communicate with other nodes to prepare phase
public class PrepareWorkerThread extends Thread {

    private final Node node;
    private final int targetPort;
    private final String targetServerName;

    private final PrepareRequest prepRequest;

    public PrepareWorkerThread(Node node, int targetPort, String targetServerName, PrepareRequest preprequest) {
        this.node = node;
        this.targetPort = targetPort;
        this.targetServerName = targetServerName;
        this.prepRequest = preprequest;
    }

    @Override
    public void run() {
        if(this.targetPort == this.node.port){
            return;
        }
        Main.node.logger.log("PrepareWorkerThread: " + this.targetServerName + " started");
        Main.node.logger.log("Server to channel: " + this.node.serversToChannel.toString());


        for(ManagedChannel a : this.node.serversToChannel.values() ){
            Main.node.logger.log(a.toString());
        }

        for(String a : this.node.serversToChannel.keySet() ){
            Main.node.logger.log(a);
        }

        ApaxosGrpc.ApaxosBlockingStub blockingStub = ApaxosGrpc.newBlockingStub(
                this.node.serversToChannel.get(this.targetServerName)
        );

        PrepareResponse resp = blockingStub.prepare(this.prepRequest);
        this.node.prepareResponseMap.put(this.targetServerName, resp);
        if(resp.getSuccess()){
            this.node.prepareAckCount++;
        }
    }
}
