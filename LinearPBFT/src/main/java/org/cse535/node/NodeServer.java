package org.cse535.node;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.bson.Document;
import org.bson.types.Binary;
import org.cse535.configs.GlobalConfigs;
import org.cse535.database.DatabaseService;
import org.cse535.loggers.LogUtils;
import org.cse535.proto.*;
import org.cse535.service.CommandsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.cse535.configs.GlobalConfigs.serversToPortMap;

public class NodeServer {

    public int port;
    public LogUtils logger;
    public LogUtils commandLogger;
    public boolean isServerActive;

    public boolean isServerByzantine;

    public String serverName;
    public Server server;

    public DatabaseService database;

    public HashMap<String, ManagedChannel> serversToChannel;
    public HashMap<String, LinearPBFTGrpc.LinearPBFTBlockingStub> serversToStub;
    public HashMap<String, CommandsGrpc.CommandsBlockingStub> serversToCommandsStub;
    public HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> serversToActivateServersStub;


    public ManagedChannel clientChannel;
    public LinearPBFTGrpc.LinearPBFTBlockingStub clientStub;


    public List<String> currentActiveServers;

    public NodeServer(String serverName, int port) {
        this.port = port;
        this.serverName = serverName;

        this.logger = new LogUtils(port);
        this.commandLogger = new LogUtils("Commands", port);

        this.currentActiveServers = new ArrayList<>( GlobalConfigs.allServers);

        initiateChannelsAndStubs();

        this.server = ServerBuilder.forPort(port)
                .addService(new CommandsService())
                .addService(new CommandsService())
                .build();

    }


    public void initiateChannelsAndStubs() {
        serversToStub = new HashMap<>();
        serversToChannel = new HashMap<>();
        serversToCommandsStub = new HashMap<>();
        isServerActive = true;
        isServerByzantine = false;

        database = new DatabaseService(this.serverName);

        serversToPortMap.forEach((serverName, port) -> {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
            System.out.println("Channel created for server: " + serverName);

            serversToChannel.put(serverName, channel);
            serversToStub.put(serverName, LinearPBFTGrpc.newBlockingStub(channel));
            serversToCommandsStub.put(serverName, CommandsGrpc.newBlockingStub(channel));
        });

        clientChannel = ManagedChannelBuilder.forAddress("localhost", GlobalConfigs.viewServerPort).usePlaintext().build();
        clientStub = LinearPBFTGrpc.newBlockingStub(clientChannel);

    }


}
