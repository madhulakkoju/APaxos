// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public interface DataGetResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DataGetResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.DatabaseSnapshot snapshot = 1;</code>
   */
  boolean hasSnapshot();
  /**
   * <code>.DatabaseSnapshot snapshot = 1;</code>
   */
  org.cse535.proto.DatabaseSnapshot getSnapshot();
  /**
   * <code>.DatabaseSnapshot snapshot = 1;</code>
   */
  org.cse535.proto.DatabaseSnapshotOrBuilder getSnapshotOrBuilder();

  /**
   * <code>bool success = 2;</code>
   */
  boolean getSuccess();
}
