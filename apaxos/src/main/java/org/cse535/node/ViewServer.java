package org.cse535.node;

import org.cse535.configs.GlobalConfigs;
import org.cse535.proto.*;
import org.cse535.service.ActivateServersService;
import org.cse535.service.TransactionPropagateService;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

import static org.cse535.configs.GlobalConfigs.allServers;

public class ViewServer extends NodeServer{

    public ViewServer(String serverName, int port) {
        super(serverName, port);
        try {
            this.server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            if (server.equals(this.serverName)) {
                System.out.println("Server: " + server + " is the current server");
                continue;
            }

            TnxPropagateGrpc.TnxPropagateBlockingStub stub = this.serversToTnxPropagateStub.get(server);

            TxnResponse response = stub.propagateTransaction(transactionInputConfig);

            if (response.getSuccess()) {
                System.out.println("Transaction propagated to server: " + server);
            } else {
                System.out.println("Transaction not propagated to server: " + server);
            }
        }

    }






    public static void main(String[] args) throws InterruptedException, IOException {

        ViewServer viewServer = new ViewServer("vs", 8000);


        HashMap<String, Boolean> activeServersStatusMap = new HashMap<>();

        for (String server : GlobalConfigs.allServers) {
            activeServersStatusMap.put(server, true);
        }

        File file = new File("C:\\Users\\mlakkoju\\apaxos-madhulakkoju\\apaxos\\src\\main\\resources\\input_file.csv");
        String line = "";
        if (file.exists()) {
            System.out.println("File exists");

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\mlakkoju\\apaxos-madhulakkoju\\apaxos\\src\\main\\resources\\input_file.csv"));

            int prevSetNumber = 0;

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                System.out.println("Line: " + line);

                TransactionInputConfig transactionInputConfig = parseTnxConfig(line);

                if (transactionInputConfig == null) {
                    System.out.println("Invalid transaction");
                    continue;
                }

                // Trigger Inactive servers to stop accepting transactions
                if (transactionInputConfig.getServerNamesList().isEmpty()) {
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

                            ActivateServersGrpc.ActivateServersBlockingStub stub = viewServer.serversToActivateServersStub.get(server);

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

                            ActivateServersGrpc.ActivateServersBlockingStub stub = viewServer.serversToActivateServersStub.get(server);

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

        }
        else {
            System.out.println("File does not exist");
        }
        viewServer.server.awaitTermination();

    }


}
