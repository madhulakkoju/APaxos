// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface BlockOfTransactionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:BlockOfTransactions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .Transaction transactions = 1;</code>
   */
  java.util.List<org.cse535.proto.Transaction> 
      getTransactionsList();
  /**
   * <code>repeated .Transaction transactions = 1;</code>
   */
  org.cse535.proto.Transaction getTransactions(int index);
  /**
   * <code>repeated .Transaction transactions = 1;</code>
   */
  int getTransactionsCount();
  /**
   * <code>repeated .Transaction transactions = 1;</code>
   */
  java.util.List<? extends org.cse535.proto.TransactionOrBuilder> 
      getTransactionsOrBuilderList();
  /**
   * <code>repeated .Transaction transactions = 1;</code>
   */
  org.cse535.proto.TransactionOrBuilder getTransactionsOrBuilder(
      int index);

  /**
   * <code>int32 blockNum = 2;</code>
   */
  int getBlockNum();

  /**
   * <code>.google.protobuf.Timestamp blockCommitTime = 3;</code>
   */
  boolean hasBlockCommitTime();
  /**
   * <code>.google.protobuf.Timestamp blockCommitTime = 3;</code>
   */
  com.google.protobuf.Timestamp getBlockCommitTime();
  /**
   * <code>.google.protobuf.Timestamp blockCommitTime = 3;</code>
   */
  com.google.protobuf.TimestampOrBuilder getBlockCommitTimeOrBuilder();

  /**
   * <code>string blockHash = 4;</code>
   */
  java.lang.String getBlockHash();
  /**
   * <code>string blockHash = 4;</code>
   */
  com.google.protobuf.ByteString
      getBlockHashBytes();

  /**
   * <code>string leader = 6;</code>
   */
  java.lang.String getLeader();
  /**
   * <code>string leader = 6;</code>
   */
  com.google.protobuf.ByteString
      getLeaderBytes();

  /**
   * <pre>
   * proposal Number
   * </pre>
   *
   * <code>int32 termNumber = 5;</code>
   */
  int getTermNumber();
}
