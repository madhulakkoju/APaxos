// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface AcceptResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AcceptResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 proposalNumber = 1;</code>
   */
  int getProposalNumber();

  /**
   * <pre>
   * request sender name
   * </pre>
   *
   * <code>string processId = 2;</code>
   */
  java.lang.String getProcessId();
  /**
   * <pre>
   * request sender name
   * </pre>
   *
   * <code>string processId = 2;</code>
   */
  com.google.protobuf.ByteString
      getProcessIdBytes();

  /**
   * <pre>
   *Current server name
   * </pre>
   *
   * <code>string acceptedServerName = 3;</code>
   */
  java.lang.String getAcceptedServerName();
  /**
   * <pre>
   *Current server name
   * </pre>
   *
   * <code>string acceptedServerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getAcceptedServerNameBytes();

  /**
   * <code>bool success = 4;</code>
   */
  boolean getSuccess();
}
