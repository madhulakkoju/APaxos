package org.cse535.node;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;

public class NodePool {

    public static int[] ports;

    public static HashMap<Integer, ManagedChannel> channelMap = new HashMap<>();

    public static void initiateNodePool() {
        NodePool.ports = new int[]{8001, 8002, 8003, 8004, 8005};

        for (int port : ports) {
            channelMap.put(port, ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build());
        }

    }

}
