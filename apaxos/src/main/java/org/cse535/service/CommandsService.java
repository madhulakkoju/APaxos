package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.proto.CommandInput;
import org.cse535.proto.CommandOutput;
import org.cse535.proto.CommandsGrpc;

public class CommandsService extends CommandsGrpc.CommandsImplBase {

    @Override
    public void printBalance(CommandInput request, StreamObserver<CommandOutput> responseObserver){

        responseObserver.onNext(CommandOutput.newBuilder().build());
        responseObserver.onCompleted();

        Main.node.printBalance();

    }

    @Override
    public void printDB(CommandInput request, StreamObserver<CommandOutput> responseObserver) {
        responseObserver.onNext(CommandOutput.newBuilder().build());
        responseObserver.onCompleted();

        Main.node.printDB();
    }

    @Override
    public void printLog(CommandInput request, StreamObserver<CommandOutput> responseObserver) {
        responseObserver.onNext(CommandOutput.newBuilder().build());
        responseObserver.onCompleted();

        Main.node.printLog();
    }

    @Override
    public void performance(CommandInput request, StreamObserver<CommandOutput> responseObserver) {
        responseObserver.onNext(CommandOutput.newBuilder().build());
        responseObserver.onCompleted();

        Main.node.printPerformance();
    }
}
