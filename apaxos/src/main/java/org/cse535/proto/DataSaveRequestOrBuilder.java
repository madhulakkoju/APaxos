// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface DataSaveRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DataSaveRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string serverName = 1;</code>
   */
  java.lang.String getServerName();
  /**
   * <code>string serverName = 1;</code>
   */
  com.google.protobuf.ByteString
      getServerNameBytes();

  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  boolean hasSnapshot();
  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  org.cse535.proto.DatabaseSnapshot getSnapshot();
  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  org.cse535.proto.DatabaseSnapshotOrBuilder getSnapshotOrBuilder();
}
