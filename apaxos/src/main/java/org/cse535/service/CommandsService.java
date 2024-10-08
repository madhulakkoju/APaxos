package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.CommandInput;
import org.cse535.proto.CommandOutput;
import org.cse535.proto.CommandsGrpc;
import org.cse535.proto.Transaction;

public class CommandsService extends CommandsGrpc.CommandsImplBase {

    @Override
    public void printBalance(CommandInput request, StreamObserver<CommandOutput> responseObserver){

        String op = " Balance: " + Main.node.getDatabase().getAccountBalance()
                + "\n Commit Balance : " + Main.node.getDatabase().getCommittedAccountBalance() + "\n-----------------";

        responseObserver.onNext(CommandOutput.newBuilder().setOutput( op ) .build());
        responseObserver.onCompleted();

        Main.node.printBalance();

    }

    @Override
    public void printDB(CommandInput request, StreamObserver<CommandOutput> responseObserver) {

        StringBuilder op = new StringBuilder(Main.node.serverName + "\n-----------------\n Balance: " + Main.node.getDatabase().getAccountBalance()
                + "\n Commit Balance : " + Main.node.getDatabase().getCommittedAccountBalance());

        for (int key : Main.node.getDatabase().getBlocks().keySet() ) {
            op.append("\n Block Number: ").append(key).append(" Block: ").append(Main.node.getDatabase().getBlocks().get(key));
        }

        op.append("\n-----------------\n\n");

        responseObserver.onNext(CommandOutput.newBuilder().setOutput(op.toString()).build());
        responseObserver.onCompleted();
        Main.commonLogger.log("Printing DB");
        Main.node.printDB();
    }

    @Override
    public void printLog(CommandInput request, StreamObserver<CommandOutput> responseObserver) {

        StringBuilder op = new StringBuilder(Main.node.serverName + "-----------------\n");
        op.append("Local Log: \n");

        for(Transaction tnx : Main.node.getDatabase().getLocalTransactionLog().getAllTransactions() ){
            op.append(tnx.toString()).append("\n");
        }

        op.append("-----------------\n\n");

        responseObserver.onNext(CommandOutput.newBuilder().setOutput(op.toString()).build());
        responseObserver.onCompleted();
        Main.commonLogger.log("Printing Log");
        Main.node.printLog();
    }

    @Override
    public void performance(CommandInput request, StreamObserver<CommandOutput> responseObserver) {
        responseObserver.onNext(CommandOutput.newBuilder().setOutput("Performance").build());
        responseObserver.onCompleted();
        Main.commonLogger.log("Printing Performance");
        Main.node.printPerformance();
    }
}
