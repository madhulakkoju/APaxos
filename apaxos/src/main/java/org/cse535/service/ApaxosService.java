package org.cse535.service;

import io.grpc.stub.StreamObserver;
import org.cse535.Main;
import org.cse535.node.Node;
import org.cse535.proto.*;

public class ApaxosService extends ApaxosGrpc.ApaxosImplBase {

    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        Main.commonLogger.log("Received prepare request from " + request.getProcessId());
        Main.commonLogger.log("Proposal Number check: " +
                request.getProcessId() + " \n requested proposal: " + request.getProposalNumber() +
                " : \n committed proposal: " + Main.node.database.getCommittedProposalNumber () +
                " \n accepted proposal: " + Main.node.database.getAcceptedproposalNumber () +
                " -> condition: " + (request.getProposalNumber() > Main.node.database.getAcceptedproposalNumber ()));
        try{
            PrepareResponse response = Main.node.handlePreparePhase(request);
            Main.commonLogger.log("Prepare response from Node");
            responseObserver.onNext(response);
            Main.commonLogger.log("Prepare response from Node -> to NEXT");
            responseObserver.onCompleted();
            Main.commonLogger.log("Prepare response sent back to client: " + response.getProcessId() + " " + response.getProposalNumber() + " : " + response.getSuccess() );
        }
        catch (Exception e){
            Main.node.logger.log("Exception: " + e.getMessage());
        }
        finally {
            Main.commonLogger.log("Finally ---------- Prepare response sent back to client");
        }
    }

    @Override
    public void accept(AcceptRequest request, StreamObserver<AcceptResponse> responseObserver) {
        Main.commonLogger.log("Received Accept request from " + request.getProcessId());
        responseObserver.onNext(Main.node.handleAcceptPhase(request));
        responseObserver.onCompleted();
    }

    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {
        Main.commonLogger.log("Received Commit request from " + request.getProcessId());
        responseObserver.onNext(Main.node.handleCommitPhase(request));
        responseObserver.onCompleted();
    }














//    // Candidates Side
//
//    public PrepareResponse handlePreparePhase(Node node, PrepareRequest request) {
//        PrepareResponse.Builder prepareResponse = PrepareResponse.newBuilder();
//        node.logger.log("Prepare Request Received from " + request.getProcessId() );
//
//        node.logger.log("Prepare Request --> Request Proposal Number: " + request.getProposalNumber()
//                + " DB Accepted Proposal Number: " + node.database.getAcceptedproposalNumber() );
//
//        if( request.getProposalNumber() > node.database.getAcceptedproposalNumber()
////              && (this.database.getLastPrepareAckTimestamp().getSeconds() >  request.getTimestamp().getSeconds() ||
////                        ( this.database.getLastPrepareAckTimestamp().getSeconds() == request.getTimestamp().getSeconds() &&
////                                this.database.getLastPrepareAckTimestamp().getNanos() > request.getTimestamp().getNanos() ) )
//        ) {
//            node.logger.log("Prepare Request Accepted from " + request.getProcessId() );
//            prepareResponse.setProposalNumber(request.getProposalNumber());
//            prepareResponse.setProcessId(request.getProcessId());
//            prepareResponse.setSuccess(true);
//
//            if(node.database.localTransactionLog.getAllTransactions() != null)
//                prepareResponse.addAllTransactions(node.database.localTransactionLog.getAllTransactions());
//
//            prepareResponse.setAcceptNumProposalNumber(node.database.getAcceptedproposalNumber());
//
//            if(node.database.getAcceptedServerId() != null)
//                prepareResponse.setAcceptNumProcessId(node.database.getAcceptedServerId());
//
//            if(node.database.getAcceptedBlockOfTransactions() != null)
//                prepareResponse.setAcceptVal(node.database.getAcceptedBlockOfTransactions());
//
//            node.database.setLastPrepareAckTimestamp(request.getTimestamp());
//            node.database.setLastPrepareAckServerId(request.getProcessId());
//
//        }
//        else{
//            prepareResponse.setSuccess(false);
//        }
//
//
//        node.logger.log("Prepare Response sent" );
//        return prepareResponse.build();
//    }
//
//    public AcceptResponse handleAcceptPhase(Node node, AcceptRequest request) {
//        AcceptResponse.Builder acceptResponse = AcceptResponse.newBuilder();
//
//        boolean acceptProposal = false;
//
//        if(node.database.getLastPrepareAckServer().equals(request.getProcessId())){
//            // Candidate is up-to-date with the Leader's previous blocks
//            if(request.getProposalNumber() == node.database.getAcceptedproposalNumber() + 1){
//                acceptProposal = true;
//                acceptResponse.setSuccess(true);
//
//            }
//            else if( request.getProposalNumber() > node.database.getAcceptedproposalNumber()){
//
//                for(int i = node.database.getCommittedProposalNumber()+1; i < request.getProposalNumber(); i++){
//
//                    node.database.commitBlock(i,
//                            request.getSyncBlocksMap().get(i),
//                            node.recomputeBalanceBeforeCommit(request.getSyncBlocksMap().get(i), node.database.getCommittedAccountBalance())
//                    );
//                }
//
//                acceptProposal = true;
//            }
//            else{
//                acceptResponse.setSuccess(false);
//            }
//        }
//        else{
//            acceptResponse.setSuccess(false);
//        }
//
//        if(acceptProposal){
//
//            node.database.leaderTransactionLog.clearTransactions();
//            node.database.leaderTransactionLog.addAllTransactions(request.getTransactionsToAcceptList());
//            node.database.leaderTransactionLog.reorderTransactionsBasedOnTimestamp();
//            node.database.leaderTransactionLog.computeBalanceAfterTransactions();
//
//            node.database.setAcceptedBlockOfTransactions(
//                    node.database.leaderTransactionLog.convertToBlockOfTransactions(request.getProposalNumber())
//            );
//
//            node.database.setAcceptedproposalNumber(request.getProposalNumber());
//            node.database.setAcceptedServerId(request.getProcessId());
//            node.database.setAccountBalance(node.database.leaderTransactionLog.BalanceAfterTransactions);
//
//            acceptResponse.setSuccess(true);
//            acceptResponse.setProposalNumber(request.getProposalNumber());
//            acceptResponse.setProcessId(request.getProcessId());
//            acceptResponse.setAcceptedServerName(node.database.currentServerId);
//
//            node.database.setLastAcceptAckTimestamp(request.getTimestamp());
//        }
//
//        return acceptResponse.build();
//    }
//
//    public CommitResponse handleCommitPhase(Node node, CommitRequest request) {
//        CommitResponse.Builder commitResponse = CommitResponse.newBuilder();
//
//        if(request.getProposalNumber() == node.database.getAcceptedproposalNumber()){
//
//            node.database.commitBlock(request.getProposalNumber(), request.getBlock(),
//                    node.recomputeBalanceBeforeCommit(request.getBlock(), node.database.getCommittedAccountBalance()));
//
//            node.database.currentProposalNumber = node.database.getCommittedProposalNumber();
//
//            commitResponse.setSuccess(true);
//            commitResponse.setProposalNumber(request.getProposalNumber());
//            commitResponse.setProcessId(request.getProcessId());
//            commitResponse.setAcceptedServerName(node.database.currentServerId);
//
//            node.database.setLastCommitTimestamp(request.getTimestamp());
//
//        }else{
//            commitResponse.setSuccess(false);
//        }
//
//        return commitResponse.build();
//    }
//
//














}
