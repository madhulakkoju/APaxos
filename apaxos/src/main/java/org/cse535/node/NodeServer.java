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
import org.cse535.proto.CommandsGrpc;
import org.cse535.proto.TnxPropagateGrpc;
import org.cse535.service.ActivateServersService;
import org.cse535.service.ApaxosService;
import org.cse535.service.CommandsService;
import org.cse535.service.TransactionPropagateService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.cse535.configs.GlobalConfigs.serversToPortMap;

public class NodeServer {

    public int port;
    public LogUtils logger;
    public LogUtils commandLogger;
    public boolean isServerActive;

    public String serverName;
    public Server server;

    public DatabaseService database;

    public HashMap<String, ManagedChannel> serversToChannel;

    public HashMap<String, ApaxosGrpc.ApaxosBlockingStub> serversToStub;
    public HashMap<String, TnxPropagateGrpc.TnxPropagateBlockingStub> serversToTnxPropagateStub;
    public HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> serversToActivateServersStub;
    public HashMap<String, CommandsGrpc.CommandsBlockingStub> serversToCommandsStub;

    public List<String> currentActiveServers;

    public NodeServer(String serverName, int port) {
        this.port = port;
        this.serverName = serverName;

        this.logger = new LogUtils(port);
        this.commandLogger = new LogUtils("Commands", port);

        this.currentActiveServers = new ArrayList<>( GlobalConfigs.allServers);

        initiateChannelsAndStubs();

        this.server = ServerBuilder.forPort(port)
                .addService(new ApaxosService())
                .addService( new ActivateServersService())
                .addService(new TransactionPropagateService())
                .addService(new CommandsService())
                .build();

    }


    public void initiateChannelsAndStubs() {
        serversToStub = new HashMap<>();
        serversToChannel = new HashMap<>();
        serversToActivateServersStub = new HashMap<>();
        serversToTnxPropagateStub = new HashMap<>();
        serversToCommandsStub = new HashMap<>();

        isServerActive = true;

        database = new DatabaseService(this.serverName);

        serversToPortMap.forEach((serverName, port) -> {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();

            System.out.println("Channel created for server: " + serverName);

            serversToChannel.put(serverName, channel);
            serversToStub.put(serverName, ApaxosGrpc.newBlockingStub(channel));
            serversToActivateServersStub.put(serverName, ActivateServersGrpc.newBlockingStub(channel));
            serversToTnxPropagateStub.put(serverName, TnxPropagateGrpc.newBlockingStub(channel));
            serversToCommandsStub.put(serverName, CommandsGrpc.newBlockingStub(channel));
        });
    }










    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public LogUtils getLogger() {
        return logger;
    }

    public void setLogger(LogUtils logger) {
        this.logger = logger;
    }

    public boolean isServerActive() {
        return isServerActive;
    }

    public void setServerActive(boolean serverActive) {
        isServerActive = serverActive;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public DatabaseService getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseService database) {
        this.database = database;
    }

    public HashMap<String, ApaxosGrpc.ApaxosBlockingStub> getServersToStub() {
        return serversToStub;
    }

    public void setServersToStub(HashMap<String, ApaxosGrpc.ApaxosBlockingStub> serversToStub) {
        this.serversToStub = serversToStub;
    }

    public HashMap<String, ManagedChannel> getServersToChannel() {
        return serversToChannel;
    }

    public void setServersToChannel(HashMap<String, ManagedChannel> serversToChannel) {
        this.serversToChannel = serversToChannel;
    }

    public HashMap<String, TnxPropagateGrpc.TnxPropagateBlockingStub> getServersToTnxPropagateStub() {
        return serversToTnxPropagateStub;
    }

    public void setServersToTnxPropagateStub(HashMap<String, TnxPropagateGrpc.TnxPropagateBlockingStub> serversToTnxPropagateStub) {
        this.serversToTnxPropagateStub = serversToTnxPropagateStub;
    }

    public HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> getServersToActivateServersStub() {
        return serversToActivateServersStub;
    }

    public void setServersToActivateServersStub(HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> serversToActivateServersStub) {
        this.serversToActivateServersStub = serversToActivateServersStub;
    }

    public HashMap<String, CommandsGrpc.CommandsBlockingStub> getServersToCommandsStub() {
        return serversToCommandsStub;
    }

    public void setServersToCommandsStub(HashMap<String, CommandsGrpc.CommandsBlockingStub> serversToCommandsStub) {
        this.serversToCommandsStub = serversToCommandsStub;
    }

    public List<String> getCurrentActiveServers() {
        return currentActiveServers;
    }

    public void setCurrentActiveServers(List<String> currentActiveServers) {
        this.currentActiveServers = currentActiveServers;
    }
}
