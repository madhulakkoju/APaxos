// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface CommitRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CommitRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 proposalNumber = 1;</code>
   */
  int getProposalNumber();

  /**
   * <pre>
   * processId is Server Name
   * </pre>
   *
   * <code>string processId = 2;</code>
   */
  java.lang.String getProcessId();
  /**
   * <pre>
   * processId is Server Name
   * </pre>
   *
   * <code>string processId = 2;</code>
   */
  com.google.protobuf.ByteString
      getProcessIdBytes();

  /**
   * <code>.BlockOfTransactions block = 3;</code>
   */
  boolean hasBlock();
  /**
   * <code>.BlockOfTransactions block = 3;</code>
   */
  org.cse535.proto.BlockOfTransactions getBlock();
  /**
   * <code>.BlockOfTransactions block = 3;</code>
   */
  org.cse535.proto.BlockOfTransactionsOrBuilder getBlockOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp timestamp = 4;</code>
   */
  boolean hasTimestamp();
  /**
   * <code>.google.protobuf.Timestamp timestamp = 4;</code>
   */
  com.google.protobuf.Timestamp getTimestamp();
  /**
   * <code>.google.protobuf.Timestamp timestamp = 4;</code>
   */
  com.google.protobuf.TimestampOrBuilder getTimestampOrBuilder();
}
