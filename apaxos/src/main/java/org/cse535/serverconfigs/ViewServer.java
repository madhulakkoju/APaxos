package org.cse535.serverconfigs;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import org.cse535.Main;
import org.cse535.loggers.LogUtils;
import org.cse535.node.ActivateServerService;
import org.cse535.node.NodePool;
import org.cse535.proto.*;
import org.cse535.transaction.TransactionService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ViewServer  {

    LogUtils logger = new LogUtils("ViewServer", 8000);
    int port = 8000;

    public Server server;

    HashMap<String, Integer> serversToPortMap ;

    HashMap<String, ActivateServersGrpc.ActivateServersBlockingStub> serversToActivateServerStub ;
    HashMap<String, TransactionServiceGrpc.TransactionServiceBlockingStub> serversToTransactionServiceStub ;

    public ViewServer() {
        server = io.grpc.ServerBuilder.forPort(port)
                .addService(new TransactionService())
                .addService(new ActivateServerService())
                .build();

        serversToPortMap = new HashMap<>();
        serversToPortMap.put("S1", 8001);
        serversToPortMap.put("S2", 8002);
        serversToPortMap.put("S3", 8003);
        serversToPortMap.put("S4", 8004);
        serversToPortMap.put("S5", 8005);

        initiateChannelsAndStubs();
    }

    public void initiateChannelsAndStubs() {

        for (Map.Entry<String, Integer> entry : serversToPortMap.entrySet()) {
            String serverName = entry.getKey();
            Integer port = entry.getValue();

            ManagedChannel channel = NodePool.channelMap.get(port);

            serversToActivateServerStub.put(serverName, ActivateServersGrpc.newBlockingStub(channel));
            serversToTransactionServiceStub.put(serverName, TransactionServiceGrpc.newBlockingStub(channel));
        }

    }

    public static TransactionInputConfig parseTnxConfig(String line) {
        String[] tnx = line.split(",");    // use comma as separator

        if(tnx.length < 3) {
            System.out.println("Invalid transaction");
            return null;
        }

        Transaction transaction = Transaction.newBuilder()
                .setSender(tnx[1])
                .setReceiver(tnx[2])
                .setAmount(Integer.parseInt(tnx[3]))
                .build();

        TransactionInputConfig transactionInputConfig = TransactionInputConfig.newBuilder()
                .setSetNumber(Integer.parseInt(tnx[0]))
                .setTransaction( transaction )
                .addAllServerNames(Arrays.asList(Arrays.copyOfRange(
                        tnx , 4, tnx.length)))
                .build();

        return transactionInputConfig;
    }

    public void sendTransactionToServers(TransactionInputConfig transactionInputConfig) {
        for (String server : transactionInputConfig.getServerNamesList()) {
            System.out.println("Sending transaction to server: " + server);

            TransactionServiceGrpc.TransactionServiceBlockingStub stub = this.serversToTransactionServiceStub.get(server);
            stub.addTransaction(transactionInputConfig.getTransaction());
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("View Server started");

        NodePool.initiateNodePool();

        String[] allServers = new String[] {
                "S1", "S2", "S3", "S4", "S5"
        };

        HashMap<String, Boolean> activeServersStatusMap = new HashMap<>();

        for (String server : allServers) {
            activeServersStatusMap.put(server, true);
        }

        ViewServer viewServer = new ViewServer();

        viewServer.server.start();
        viewServer.server.awaitTermination();

        File file = new File("./scripts/input_file.csv");
        String line = "";
        if (file.exists()) {
            System.out.println("File exists");

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader("./scripts/input_file.csv"));

            int prevSetNumber = 0;

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                TransactionInputConfig transactionInputConfig = parseTnxConfig(line);

                if (transactionInputConfig == null) {
                    System.out.println("Invalid transaction");
                    continue;
                }

                // Trigger Inactive servers to stop accepting transactions
                if (transactionInputConfig.getServerNamesList() == null || transactionInputConfig.getServerNamesList().isEmpty()) {
                    System.out.println("No servers to send the transaction to");
                    continue;
                }

                //Activate or deactivate Servers
                if(prevSetNumber != transactionInputConfig.getSetNumber()) {
                    prevSetNumber = transactionInputConfig.getSetNumber();

                    // If the Test Set Number changes, then trigger the inactive servers to stop accepting transactions

                    // Set all servers inactive
                    for (String server : allServers) {
                        activeServersStatusMap.put(server, false);
                    }
                    // Set the active servers
                    for (String server : transactionInputConfig.getServerNamesList()) {
                        activeServersStatusMap.put(server, true);
                    }

                    for( String server : allServers) {
                        if(activeServersStatusMap.get(server)) {
                            System.out.println("Server: " + server + " is active");

                            ActivateServerRequest request = ActivateServerRequest.newBuilder()
                                    .setServerName(server)
                                    .build();

                            ActivateServersGrpc.ActivateServersBlockingStub stub = viewServer.serversToActivateServerStub.get(server);

                            ActivateServerResponse response = stub.activateServer(request);

                            if(response.getSuccess()) {
                                System.out.println("Server: " + server + " is activated");
                            } else {
                                System.out.println("Server: " + server + " is not activated");
                            }

                        } else {
                            System.out.println("Server: " + server + " is inactive");

                            DeactivateServerRequest request = DeactivateServerRequest.newBuilder()
                                    .setServerName(server)
                                    .build();

                            ActivateServersGrpc.ActivateServersBlockingStub stub = viewServer.serversToActivateServerStub.get(server);

                            DeactivateServerResponse response = stub.deactivateServer(request);

                            if(response.getSuccess()) {
                                System.out.println("Server: " + server + " is deactivated");
                            } else {
                                System.out.println("Server: " + server + " is not deactivated");
                            }

                        }
                    }
                }
                else{
                    System.out.println("Same set number");
                }

                // Multicast Transactions to active servers
                viewServer.sendTransactionToServers(transactionInputConfig);
            }

        } else {
            System.out.println("File does not exist");
        }




    }
}
