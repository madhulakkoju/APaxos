package org.cse535.threadimpls;

import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import org.cse535.node.Node;
import org.cse535.node.NodePool;
import org.cse535.proto.TransactionServiceGrpc;

import java.util.HashMap;

import static com.google.protobuf.util.Timestamps.fromMillis;
import static java.lang.System.currentTimeMillis;

public class BlockCastThread extends Thread{

    private final Node node;
    private final int targetPort;
    private final HashMap<Integer, Boolean> blockCastMap;

    public BlockCastThread(int port, Node node, HashMap<Integer, Boolean> blockCastMap) {
        this.node = node;
        this.targetPort = port;
        this.blockCastMap = blockCastMap;
    }

    @Override
    public void run() {

        if(this.node.port == this.targetPort) {
            return;
        }

         ManagedChannel channel = NodePool.channelMap.get(this.targetPort);

         TransactionServiceGrpc.TransactionServiceBlockingStub stub = TransactionServiceGrpc.newBlockingStub(channel);

         Timestamp timestamp = fromMillis(currentTimeMillis());

//         CommitBlockRequest request = CommitBlockRequest.newBuilder()
//                    .setBlock( block )
//                 .setTimestamp( timestamp )
//                 .build();
//
//         SyncRequest request = SyncRequest.newBuilder()
//                 .setTimestamp( timestamp )
//                 .build();
//         SyncResponse response = stub.synchronize( request );
//
//         if(response.getSuccess()) {
//
//             this.syncMap.put(this.targetPort, true);
//
//             LeaderSyncTransactionStore.addTransactions(response.getTransactionsList());
//
//             node.logger.log("Successfully synchronized with node on port: " + this.targetPort);
//         }
//         else {
//             this.syncMap.put(this.targetPort, false);
//             node.logger.log("Failed to synchronize with node on port: " + this.targetPort);
//         }
    }

}
