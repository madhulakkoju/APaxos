package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.configs.Utils;
import org.cse535.proto.CommandInput;
import org.cse535.proto.CommandOutput;
import org.cse535.proto.CommandsGrpc;
import org.cse535.proto.Transaction;

public class CommandsService extends CommandsGrpc.CommandsImplBase {

    @Override
    public void printBalance(CommandInput request, StreamObserver<CommandOutput> responseObserver){
        String op = Main.node.printBalance();
        responseObserver.onNext(CommandOutput.newBuilder().setOutput( op ) .build());
        responseObserver.onCompleted();

        Main.node.commandLogger.log(op);

    }

    @Override
    public void printDB(CommandInput request, StreamObserver<CommandOutput> responseObserver) {

        String op = Main.node.printDB();

        responseObserver.onNext(CommandOutput.newBuilder().setOutput(op).build());
        responseObserver.onCompleted();

        Main.node.commandLogger.log(op);
    }

    @Override
    public void printLog(CommandInput request, StreamObserver<CommandOutput> responseObserver) {

        String op = Main.node.printLog();

        responseObserver.onNext(CommandOutput.newBuilder().setOutput(op).build());
        responseObserver.onCompleted();

        Main.node.commandLogger.log(op);

    }

    @Override
    public void performance(CommandInput request, StreamObserver<CommandOutput> responseObserver) {
        String op = Main.node.printPerformance();
        responseObserver.onNext(CommandOutput.newBuilder().setOutput(op).build());
        responseObserver.onCompleted();

        Main.node.commandLogger.log(op);
    }

    @Override
    public void printClientBalance(CommandInput request, StreamObserver<CommandOutput> responseObserver) {

        responseObserver.onNext(CommandOutput.newBuilder().setOutput( request.getInput()+
                " Balance on "+Main.node.getServerName()+
                " is = "+ Main.node.getDatabase().computeClientsBalance( request.getInput() ) ).build());
        responseObserver.onCompleted();

    }
}
