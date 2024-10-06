// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface PrepareResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PrepareResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 proposalNumber = 1;</code>
   */
  int getProposalNumber();

  /**
   * <code>string processId = 2;</code>
   */
  java.lang.String getProcessId();
  /**
   * <code>string processId = 2;</code>
   */
  com.google.protobuf.ByteString
      getProcessIdBytes();

  /**
   * <pre>
   * response T(S) from accepting server
   * </pre>
   *
   * <code>repeated .Transaction transactions = 4;</code>
   */
  java.util.List<org.cse535.proto.Transaction> 
      getTransactionsList();
  /**
   * <pre>
   * response T(S) from accepting server
   * </pre>
   *
   * <code>repeated .Transaction transactions = 4;</code>
   */
  org.cse535.proto.Transaction getTransactions(int index);
  /**
   * <pre>
   * response T(S) from accepting server
   * </pre>
   *
   * <code>repeated .Transaction transactions = 4;</code>
   */
  int getTransactionsCount();
  /**
   * <pre>
   * response T(S) from accepting server
   * </pre>
   *
   * <code>repeated .Transaction transactions = 4;</code>
   */
  java.util.List<? extends org.cse535.proto.TransactionOrBuilder> 
      getTransactionsOrBuilderList();
  /**
   * <pre>
   * response T(S) from accepting server
   * </pre>
   *
   * <code>repeated .Transaction transactions = 4;</code>
   */
  org.cse535.proto.TransactionOrBuilder getTransactionsOrBuilder(
      int index);

  /**
   * <pre>
   * previous AcceptNum
   * </pre>
   *
   * <code>int32 AcceptNumProposalNumber = 5;</code>
   */
  int getAcceptNumProposalNumber();

  /**
   * <pre>
   * previous AcceptNum
   * </pre>
   *
   * <code>string AcceptNumProcessId = 6;</code>
   */
  java.lang.String getAcceptNumProcessId();
  /**
   * <pre>
   * previous AcceptNum
   * </pre>
   *
   * <code>string AcceptNumProcessId = 6;</code>
   */
  com.google.protobuf.ByteString
      getAcceptNumProcessIdBytes();

  /**
   * <code>.BlockOfTransactions AcceptVal = 7;</code>
   */
  boolean hasAcceptVal();
  /**
   * <code>.BlockOfTransactions AcceptVal = 7;</code>
   */
  org.cse535.proto.BlockOfTransactions getAcceptVal();
  /**
   * <code>.BlockOfTransactions AcceptVal = 7;</code>
   */
  org.cse535.proto.BlockOfTransactionsOrBuilder getAcceptValOrBuilder();

  /**
   * <code>bool success = 3;</code>
   */
  boolean getSuccess();
}
