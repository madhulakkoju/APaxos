package org.cse535.node;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.cse535.configs.GlobalConfigs;
import org.cse535.database.DatabaseService;
import org.cse535.loggers.LogUtils;
import org.cse535.proto.ActivateServersGrpc;
import org.cse535.proto.ApaxosGrpc;
import org.cse535.proto.TnxPropagateGrpc;
import org.cse535.service.ActivateServersService;
import org.cse535.service.ApaxosService;
import org.cse535.service.TransactionPropagateService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.cse535.configs.GlobalConfigs.serversToPortMap;

public class NodeServer {

    public int port;
    public LogUtils logger;
    public boolean isServerActive;

    public String serverName;
    public Server server;

    public DatabaseService database;

    public HashMap<String, ManagedChannel> serversToChannel;

    public HashMap<String, ApaxosGrpc.ApaxosBlockingStub> serversToStub;
    public HashMap<String, TnxPropagateGrpc.TnxPropagateBlockingStub> serversToTnxPropagateStub;
    public HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> serversToActivateServersStub;

    public List<String> currentActiveServers;

    public NodeServer(String serverName, int port) {
        this.port = port;
        this.serverName = serverName;

        this.logger = new LogUtils(port);

        this.currentActiveServers = new ArrayList<>( GlobalConfigs.allServers);

        initiateChannelsAndStubs();

        this.server = ServerBuilder.forPort(port)
                .addService(new ApaxosService())
                .addService( new ActivateServersService())
                .addService(new TransactionPropagateService())
                .build();

    }


    public void initiateChannelsAndStubs() {
        serversToStub = new HashMap<>();
        serversToChannel = new HashMap<>();
        serversToActivateServersStub = new HashMap<>();
        serversToTnxPropagateStub = new HashMap<>();

        isServerActive = true;

        database = new DatabaseService();

        serversToPortMap.forEach((serverName, port) -> {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();

            System.out.println("Channel created for server: " + serverName);

            serversToStub.put(serverName, ApaxosGrpc.newBlockingStub(channel));
            serversToActivateServersStub.put(serverName, ActivateServersGrpc.newBlockingStub(channel));
            serversToTnxPropagateStub.put(serverName, TnxPropagateGrpc.newBlockingStub(channel));
        });
    }












}
