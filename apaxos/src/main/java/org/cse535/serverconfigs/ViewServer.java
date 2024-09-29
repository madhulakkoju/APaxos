package org.cse535.serverconfigs;

import io.grpc.Server;
import org.cse535.loggers.LogUtils;
import org.cse535.proto.Transaction;
import org.cse535.transaction.TransactionService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ViewServer {

    LogUtils logger = new LogUtils("ViewServer", 8000);
    int port = 8000;

    public Server server;

    public ViewServer() {
        server = io.grpc.ServerBuilder.forPort(port)
                .addService(new TransactionService())
                .build();
    }










    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("View Server started");

        List<NodeConfig> nodes = new ArrayList<>();

        nodes.add(new NodeConfig("A", "S1", 8001));
        nodes.add(new NodeConfig("B", "S2", 8002));
        nodes.add(new NodeConfig("C", "S3", 8003));
        nodes.add(new NodeConfig("D", "S4", 8004));
        nodes.add(new NodeConfig("E", "S5", 8005));

        for (NodeConfig node : nodes) {
            System.out.println(
                    " Node: " + node.clientName +
                    " Server: " + node.serverName +
                    " Port: " + node.port
            );

        }


        HashMap<String, Integer> serversToPortMap = new HashMap<>();
        serversToPortMap.put("S1", 8001);
        serversToPortMap.put("S2", 8002);
        serversToPortMap.put("S3", 8003);
        serversToPortMap.put("S4", 8004);
        serversToPortMap.put("S5", 8005);

        String[] currentActiveServers = new String[]{
                "S1", "S2", "S3", "S4", "S5"
        };

        HashMap<String, Boolean> activeServersStatusMap = new HashMap<>();

        for (String server : currentActiveServers) {
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
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] tnx = line.split(",");    // use comma as separator

                if(tnx.length < 3) {
                    System.out.println("Invalid transaction");
                    continue;
                }


                Transaction transaction = Transaction.newBuilder()
                        .setSender(tnx[0])
                        .setReceiver(tnx[1])
                        .setAmount(Integer.parseInt(tnx[2]))
                        .build();

                HashSet<String> currentActiveServersSet = new HashSet<>(Arrays.asList(Arrays.copyOfRange(
                        tnx , 3, tnx.length)));

                for (String server : currentActiveServers) {
                    if (!currentActiveServersSet.contains(server)) {
                        activeServersStatusMap.put(server, false);
                    }
                    else{
                        activeServersStatusMap.put(server, true);
                    }
                }

                // Trigger Inactive servers to stop accepting transactions



                // Multicast the transaction to all the active servers





            }

        } else {
            System.out.println("File does not exist");
        }




    }
}
