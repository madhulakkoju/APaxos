// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: linearpbft.proto

package org.cse535.proto;

public interface ExecutionReplyRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ExecutionReplyRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 view = 1;</code>
   */
  int getView();

  /**
   * <code>int32 sequenceNumber = 2;</code>
   */
  int getSequenceNumber();

  /**
   * <code>string processId = 3;</code>
   */
  java.lang.String getProcessId();
  /**
   * <code>string processId = 3;</code>
   */
  com.google.protobuf.ByteString
      getProcessIdBytes();

  /**
   * <code>int32 transactionId = 5;</code>
   */
  int getTransactionId();

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
