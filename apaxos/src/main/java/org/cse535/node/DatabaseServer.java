package org.cse535.node;

import io.grpc.ServerBuilder;
import org.cse535.configs.GlobalConfigs;
import org.cse535.proto.DatabaseSnapshot;
import org.cse535.proto.databaseGrpc;
import org.cse535.service.DatabaseBackupService;

import java.util.HashMap;

public class DatabaseServer extends NodeServer {

    public HashMap<String, DatabaseSnapshot> databaseSnapshots;

    public DatabaseServer(String serverName, int port) {
        super(serverName, port);
        databaseSnapshots = new HashMap<>();

        this.server = ServerBuilder.forPort(port)
                .addService(new DatabaseBackupService())
                .build();

        try {
            this.server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static DatabaseServer activeServer;

    public static void main(String[] args) throws InterruptedException {
        activeServer = new DatabaseServer(GlobalConfigs.databaseServerName, GlobalConfigs.databaseServerPort);



        activeServer.server.awaitTermination();
    }
}
