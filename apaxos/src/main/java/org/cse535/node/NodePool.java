package org.cse535.node;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;

public class NodePool {

    public static int[] ports;

    public static ManagedChannel[] channels;

    public static HashMap<Integer, ManagedChannel> channelMap = new HashMap<>();

    public static void initChannels() {
        for (int port : ports) {
            channelMap.put(port, ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build());
        }
    }

}
