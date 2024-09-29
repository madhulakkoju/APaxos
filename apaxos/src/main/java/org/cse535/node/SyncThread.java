package org.cse535.node;

import io.grpc.ManagedChannel;
import org.cse535.database.LeaderSyncTransactionStore;
import org.cse535.proto.SyncRequest;
import org.cse535.proto.SyncResponse;
import org.cse535.proto.TransactionServiceGrpc;


import static com.google.protobuf.util.Timestamps.fromMillis;
import static java.lang.System.currentTimeMillis;
import com.google.protobuf.Timestamp;

import java.util.HashMap;

public class SyncThread extends Thread {

    private final Node node;
    private final int targetPort;

    public HashMap<Integer, Boolean> syncMap;

    public SyncThread(int port, Node node, HashMap<Integer, Boolean> syncMap) {
        this.node = node;
        this.targetPort = port;
        this.syncMap = syncMap;
    }

    @Override
    public void run() {

        if(this.node.port == this.targetPort) {
            return;
        }

        ManagedChannel channel = NodePool.channelMap.get(this.targetPort);

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = TransactionServiceGrpc.newBlockingStub(channel);

        Timestamp timestamp = fromMillis(currentTimeMillis());

        SyncRequest request = SyncRequest.newBuilder()
                .setTimestamp( timestamp )
                .build();
        SyncResponse response = stub.synchronize( request );

        if(response.getSuccess()) {

            this.syncMap.put(this.targetPort, true);

            LeaderSyncTransactionStore.addTransactions(response.getTransactionsList());

            node.logger.log("Successfully synchronized with node on port: " + this.targetPort);
        }
        else {
            this.syncMap.put(this.targetPort, false);
            node.logger.log("Failed to synchronize with node on port: " + this.targetPort);
        }
    }
}
