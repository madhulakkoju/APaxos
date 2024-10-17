package org.cse535.node;


import org.bson.Document;
import org.cse535.configs.GlobalConfigs;
import org.cse535.proto.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.cse535.configs.GlobalConfigs.allServers;

public class ViewServer extends NodeServer{

    public enum Command {
        PrintDB,
        PrintBalance,
        PrintLog,
        Performance,
        PrintClientBalances
    }

    public ViewServer(String serverName, int port) {
        super(serverName, port);
        try {
            this.server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TransactionInputConfig parseTnxConfig(String line, int tnxCount) {
        String[] tnx = line.replace("(","").replace(")","")
                .replace("[","").replace("]","")
                .replace(" ","").replace("\"","")
                .split(",");    // use comma as separator

        if(tnx.length < 3) {
          //  System.out.println("Invalid transaction");
            return null;
        }

        Transaction transaction = Transaction.newBuilder()
                .setSender(tnx[1])
                .setReceiver(tnx[2])
                .setAmount(Integer.parseInt(tnx[3]))
                .setTransactionNum(tnxCount)
                .build();

        return TransactionInputConfig.newBuilder()
                .setSetNumber(Integer.parseInt(tnx[0]))
                .setTransaction( transaction )
                .addAllServerNames(Arrays.asList(Arrays.copyOfRange(
                        tnx , 4, tnx.length)))
                .build();
    }

    public void sendTransactionToServers(TransactionInputConfig transactionInputConfig) {

        //for (String server : transactionInputConfig.getServerNamesList()) {
        for (String server : GlobalConfigs.allServers ) {
            if (server.equals(this.serverName)) {
                //System.out.println("Server: " + server + " is the current server");
                continue;
            }

            TnxPropagateGrpc.TnxPropagateBlockingStub stub = this.serversToTnxPropagateStub.get(server);

            TxnResponse response = stub.propagateTransaction(transactionInputConfig);

//            if (response.getSuccess()) {
//                System.out.println("Transaction propagated to server: " + server);
//            } else {
//                System.out.println("Transaction not propagated to server: " + server);
//            }
        }

    }

    public void sendCommandToServers(Command commandType, HashMap<String, Boolean> activeServersStatusMap) throws InterruptedException {
        CommandInput commandInput = CommandInput.newBuilder().build();

        Thread.sleep(10);


        // For only Active Servers

//        activeServersStatusMap.forEach((server, isActive) -> {
//            if (!server.equals(this.serverName) && isActive) {
//                CommandsGrpc.CommandsBlockingStub stub = this.serversToCommandsStub.get(server);
//                CommandOutput op  = CommandOutput.newBuilder().setOutput("No Output").build() ;
//
//                switch (commandType) {
//                    case PrintDB:
//                        op = stub.printDB(commandInput);
//                        break;
//                    case PrintBalance:
//                        op = stub.printBalance(commandInput);
//                        break;
//                    case PrintLog:
//                        op = stub.printLog(commandInput);
//                        break;
//                    case Performance:
//                        op = stub.performance(commandInput);
//                        break;
//                }
//
//                this.logger.log("Command: " + commandType + "\n server: " + server + "\n output: \n"+ op.getOutput());
//                //System.out.println("Command: " + commandType + "\n server: " + server + "\n output: \n"+ op.getOutput());
//            }
//        });

        activeServersStatusMap.forEach((server, isActive) -> {

            CommandsGrpc.CommandsBlockingStub stub = this.serversToCommandsStub.get(server);
            CommandOutput op  = CommandOutput.newBuilder().setOutput("No Output").build() ;

            switch (commandType) {
                case PrintDB:
                    op = stub.printDB(commandInput);
                    break;
                case PrintBalance:
                    op = stub.printBalance(commandInput);
                    break;
                case PrintLog:
                    op = stub.printLog(commandInput);
                    break;
                case Performance:
                    op = stub.performance(commandInput);
                    break;
                case PrintClientBalances:
                    CommandInput.Builder bufCommandInput = CommandInput.newBuilder();
                    StringBuilder clientBalanceOutput = new StringBuilder(" Client Balances Across Servers: on "+ server +"\n");
                    for (String client : allServers ) {
                        clientBalanceOutput.append(stub.printClientBalance( bufCommandInput.setInput(client).build() ).getOutput() ).append("\n");
                    }
                    op = CommandOutput.newBuilder().setOutput(clientBalanceOutput.toString()).build();
                    break;
            }

            this.logger.log("Command: " + commandType + "\n server: " + server + "\n output: \n"+ op.getOutput());

        });

    }




    public static void main(String[] args) throws InterruptedException, IOException {

        ViewServer viewServer = new ViewServer(GlobalConfigs.viewServerName, GlobalConfigs.viewServerPort);

        HashSet<String> commandsSet = new HashSet<>();
        commandsSet.add("PrintDB");
        commandsSet.add("PrintBalance");
        commandsSet.add("PrintLog");
        commandsSet.add("Performance");
        commandsSet.add("PrintClientBalances");



        //Clear out data base for testing
        viewServer.mongoDBCollection.deleteMany(new Document());
        viewServer.mongoDBDetailCollection.deleteMany(new Document());





        int tnxCount = 1;
        int lineNum = 0;

        HashMap<String, Boolean> activeServersStatusMap = new HashMap<>();

        for (String server : GlobalConfigs.allServers) {
            activeServersStatusMap.put(server, true);
        }

        String path = "src/main/resources/lab1_Test.csv";

        //File file = new File("C:\\Users\\mlakkoju\\apaxos-madhulakkoju\\apaxos\\src\\main\\resources\\input_file.csv");
        File file = new File(path);
        String line;
        if (file.exists()) {
            System.out.println("File exists");

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader(path));

            int prevSetNumber = 0;

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                lineNum++;

                Thread.sleep(5);

               // System.out.println("Line: " + line);
                viewServer.logger.log("-------------------------------------------------------------\nLine: "+ lineNum +" : "+ line);

                TransactionInputConfig transactionInputConfig = parseTnxConfig(line, tnxCount++);

                if (transactionInputConfig == null) {
                    //System.out.println("Invalid transaction");
                    tnxCount -- ;

                    if(commandsSet.contains(line)) {
                        viewServer.sendCommandToServers(Command.valueOf(line), activeServersStatusMap);
                    }
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

                    Thread.sleep(100);
                    System.out.println("Press Enter to continue to next Test set. This will activate the servers and publish transactions to servers."+transactionInputConfig.getSetNumber());
                    String a  = System.console().readLine();

                    viewServer.sendCommandToServers(Command.valueOf("PrintBalance"), activeServersStatusMap);
                    viewServer.sendCommandToServers(Command.valueOf("PrintDB"), activeServersStatusMap);

                    for( String server : allServers) {
                        if(activeServersStatusMap.get(server)) {
                            //System.out.println("Server: " + server + " is active");

                            ActivateServerRequest request = ActivateServerRequest.newBuilder()
                                    .setServerName(server)
                                    .build();

                            ActivateServerResponse response = viewServer.serversToActivateServersStub.get(server).activateServer(request);

//                            if(response.getSuccess()) {
//                                System.out.println("Server: " + server + " is activated");
//                            } else {
//                                System.out.println("Server: " + server + " is not activated");
//                            }

                        } else {
                           // System.out.println("Server: " + server + " is inactive");

                            DeactivateServerRequest request = DeactivateServerRequest.newBuilder()
                                    .setServerName(server)
                                    .build();

                            ActivateServersGrpc.ActivateServersBlockingStub stub = viewServer.serversToActivateServersStub.get(server);

                            DeactivateServerResponse response = stub.deactivateServer(request);
//
//                            if(response.getSuccess()) {
//                                System.out.println("Server: " + server + " is deactivated");
//                            } else {
//                                System.out.println("Server: " + server + " is not deactivated");
//                            }

                        }
                    }
                }
                else{
                    //System.out.println("Same set number");
                }

                // Multicast Transactions to active servers
                viewServer.sendTransactionToServers(transactionInputConfig);
            }

        }
        else {
            System.out.println("File does not exist");
        }

        System.out.println("Running All Commands on all servers");
        Thread.sleep(1000);
        viewServer.sendCommandToServers(Command.valueOf("PrintBalance"), activeServersStatusMap);
        viewServer.sendCommandToServers(Command.valueOf("PrintLog"), activeServersStatusMap);
        viewServer.sendCommandToServers(Command.valueOf("PrintDB"), activeServersStatusMap);
        viewServer.sendCommandToServers(Command.valueOf("PrintClientBalances"), activeServersStatusMap);
        viewServer.sendCommandToServers(Command.valueOf("Performance"), activeServersStatusMap);

        System.out.println("All the Commands have been executed and you can find outputs in Logs. \nFreestyle from now on. use Commands only. Type STOP to stop the view server / Client");

        while(true) {
            String inputCommand = System.console().readLine();

            if(commandsSet.contains(inputCommand)) {
                viewServer.sendCommandToServers(Command.valueOf(inputCommand), activeServersStatusMap);
            }
            if(inputCommand.equalsIgnoreCase("STOP")) {
                break;
            }
        }


        viewServer.server.awaitTermination();

    }


}
