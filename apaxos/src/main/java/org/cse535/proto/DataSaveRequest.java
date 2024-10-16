// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

/**
 * Protobuf type {@code DataSaveRequest}
 */
public  final class DataSaveRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DataSaveRequest)
    DataSaveRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DataSaveRequest.newBuilder() to construct.
  private DataSaveRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DataSaveRequest() {
    serverName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DataSaveRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            serverName_ = s;
            break;
          }
          case 18: {
            org.cse535.proto.DatabaseSnapshot.Builder subBuilder = null;
            if (snapshot_ != null) {
              subBuilder = snapshot_.toBuilder();
            }
            snapshot_ = input.readMessage(org.cse535.proto.DatabaseSnapshot.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(snapshot_);
              snapshot_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.cse535.proto.ApaxosOuterClass.internal_static_DataSaveRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.cse535.proto.ApaxosOuterClass.internal_static_DataSaveRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.cse535.proto.DataSaveRequest.class, org.cse535.proto.DataSaveRequest.Builder.class);
  }

  public static final int SERVERNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object serverName_;
  /**
   * <code>string serverName = 1;</code>
   */
  public java.lang.String getServerName() {
    java.lang.Object ref = serverName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      serverName_ = s;
      return s;
    }
  }
  /**
   * <code>string serverName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getServerNameBytes() {
    java.lang.Object ref = serverName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      serverName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SNAPSHOT_FIELD_NUMBER = 2;
  private org.cse535.proto.DatabaseSnapshot snapshot_;
  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  public boolean hasSnapshot() {
    return snapshot_ != null;
  }
  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  public org.cse535.proto.DatabaseSnapshot getSnapshot() {
    return snapshot_ == null ? org.cse535.proto.DatabaseSnapshot.getDefaultInstance() : snapshot_;
  }
  /**
   * <code>.DatabaseSnapshot snapshot = 2;</code>
   */
  public org.cse535.proto.DatabaseSnapshotOrBuilder getSnapshotOrBuilder() {
    return getSnapshot();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getServerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, serverName_);
    }
    if (snapshot_ != null) {
      output.writeMessage(2, getSnapshot());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getServerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, serverName_);
    }
    if (snapshot_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getSnapshot());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.cse535.proto.DataSaveRequest)) {
      return super.equals(obj);
    }
    org.cse535.proto.DataSaveRequest other = (org.cse535.proto.DataSaveRequest) obj;

    boolean result = true;
    result = result && getServerName()
        .equals(other.getServerName());
    result = result && (hasSnapshot() == other.hasSnapshot());
    if (hasSnapshot()) {
      result = result && getSnapshot()
          .equals(other.getSnapshot());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SERVERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getServerName().hashCode();
    if (hasSnapshot()) {
      hash = (37 * hash) + SNAPSHOT_FIELD_NUMBER;
      hash = (53 * hash) + getSnapshot().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.cse535.proto.DataSaveRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.cse535.proto.DataSaveRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.cse535.proto.DataSaveRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.cse535.proto.DataSaveRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.cse535.proto.DataSaveRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code DataSaveRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DataSaveRequest)
      org.cse535.proto.DataSaveRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.cse535.proto.ApaxosOuterClass.internal_static_DataSaveRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.cse535.proto.ApaxosOuterClass.internal_static_DataSaveRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.cse535.proto.DataSaveRequest.class, org.cse535.proto.DataSaveRequest.Builder.class);
    }

    // Construct using org.cse535.proto.DataSaveRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      serverName_ = "";

      if (snapshotBuilder_ == null) {
        snapshot_ = null;
      } else {
        snapshot_ = null;
        snapshotBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.cse535.proto.ApaxosOuterClass.internal_static_DataSaveRequest_descriptor;
    }

    @java.lang.Override
    public org.cse535.proto.DataSaveRequest getDefaultInstanceForType() {
      return org.cse535.proto.DataSaveRequest.getDefaultInstance();
    }

    @java.lang.Override
    public org.cse535.proto.DataSaveRequest build() {
      org.cse535.proto.DataSaveRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.cse535.proto.DataSaveRequest buildPartial() {
      org.cse535.proto.DataSaveRequest result = new org.cse535.proto.DataSaveRequest(this);
      result.serverName_ = serverName_;
      if (snapshotBuilder_ == null) {
        result.snapshot_ = snapshot_;
      } else {
        result.snapshot_ = snapshotBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.cse535.proto.DataSaveRequest) {
        return mergeFrom((org.cse535.proto.DataSaveRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.cse535.proto.DataSaveRequest other) {
      if (other == org.cse535.proto.DataSaveRequest.getDefaultInstance()) return this;
      if (!other.getServerName().isEmpty()) {
        serverName_ = other.serverName_;
        onChanged();
      }
      if (other.hasSnapshot()) {
        mergeSnapshot(other.getSnapshot());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.cse535.proto.DataSaveRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.cse535.proto.DataSaveRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object serverName_ = "";
    /**
     * <code>string serverName = 1;</code>
     */
    public java.lang.String getServerName() {
      java.lang.Object ref = serverName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        serverName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string serverName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getServerNameBytes() {
      java.lang.Object ref = serverName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        serverName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string serverName = 1;</code>
     */
    public Builder setServerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      serverName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string serverName = 1;</code>
     */
    public Builder clearServerName() {
      
      serverName_ = getDefaultInstance().getServerName();
      onChanged();
      return this;
    }
    /**
     * <code>string serverName = 1;</code>
     */
    public Builder setServerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      serverName_ = value;
      onChanged();
      return this;
    }

    private org.cse535.proto.DatabaseSnapshot snapshot_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.cse535.proto.DatabaseSnapshot, org.cse535.proto.DatabaseSnapshot.Builder, org.cse535.proto.DatabaseSnapshotOrBuilder> snapshotBuilder_;
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public boolean hasSnapshot() {
      return snapshotBuilder_ != null || snapshot_ != null;
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public org.cse535.proto.DatabaseSnapshot getSnapshot() {
      if (snapshotBuilder_ == null) {
        return snapshot_ == null ? org.cse535.proto.DatabaseSnapshot.getDefaultInstance() : snapshot_;
      } else {
        return snapshotBuilder_.getMessage();
      }
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public Builder setSnapshot(org.cse535.proto.DatabaseSnapshot value) {
      if (snapshotBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        snapshot_ = value;
        onChanged();
      } else {
        snapshotBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public Builder setSnapshot(
        org.cse535.proto.DatabaseSnapshot.Builder builderForValue) {
      if (snapshotBuilder_ == null) {
        snapshot_ = builderForValue.build();
        onChanged();
      } else {
        snapshotBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public Builder mergeSnapshot(org.cse535.proto.DatabaseSnapshot value) {
      if (snapshotBuilder_ == null) {
        if (snapshot_ != null) {
          snapshot_ =
            org.cse535.proto.DatabaseSnapshot.newBuilder(snapshot_).mergeFrom(value).buildPartial();
        } else {
          snapshot_ = value;
        }
        onChanged();
      } else {
        snapshotBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public Builder clearSnapshot() {
      if (snapshotBuilder_ == null) {
        snapshot_ = null;
        onChanged();
      } else {
        snapshot_ = null;
        snapshotBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public org.cse535.proto.DatabaseSnapshot.Builder getSnapshotBuilder() {
      
      onChanged();
      return getSnapshotFieldBuilder().getBuilder();
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    public org.cse535.proto.DatabaseSnapshotOrBuilder getSnapshotOrBuilder() {
      if (snapshotBuilder_ != null) {
        return snapshotBuilder_.getMessageOrBuilder();
      } else {
        return snapshot_ == null ?
            org.cse535.proto.DatabaseSnapshot.getDefaultInstance() : snapshot_;
      }
    }
    /**
     * <code>.DatabaseSnapshot snapshot = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.cse535.proto.DatabaseSnapshot, org.cse535.proto.DatabaseSnapshot.Builder, org.cse535.proto.DatabaseSnapshotOrBuilder> 
        getSnapshotFieldBuilder() {
      if (snapshotBuilder_ == null) {
        snapshotBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.cse535.proto.DatabaseSnapshot, org.cse535.proto.DatabaseSnapshot.Builder, org.cse535.proto.DatabaseSnapshotOrBuilder>(
                getSnapshot(),
                getParentForChildren(),
                isClean());
        snapshot_ = null;
      }
      return snapshotBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:DataSaveRequest)
  }

  // @@protoc_insertion_point(class_scope:DataSaveRequest)
  private static final org.cse535.proto.DataSaveRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.cse535.proto.DataSaveRequest();
  }

  public static org.cse535.proto.DataSaveRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DataSaveRequest>
      PARSER = new com.google.protobuf.AbstractParser<DataSaveRequest>() {
    @java.lang.Override
    public DataSaveRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DataSaveRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DataSaveRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DataSaveRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.cse535.proto.DataSaveRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

