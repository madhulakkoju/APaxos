// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: linearpbft.proto

package org.cse535.proto;

/**
 * Protobuf type {@code TransactionInputConfig}
 */
public  final class TransactionInputConfig extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TransactionInputConfig)
    TransactionInputConfigOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransactionInputConfig.newBuilder() to construct.
  private TransactionInputConfig(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransactionInputConfig() {
    setNumber_ = 0;
    view_ = 0;
    serverNames_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TransactionInputConfig(
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
          case 8: {

            setNumber_ = input.readInt32();
            break;
          }
          case 18: {
            org.cse535.proto.Transaction.Builder subBuilder = null;
            if (transaction_ != null) {
              subBuilder = transaction_.toBuilder();
            }
            transaction_ = input.readMessage(org.cse535.proto.Transaction.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(transaction_);
              transaction_ = subBuilder.buildPartial();
            }

            break;
          }
          case 24: {

            view_ = input.readInt32();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              serverNames_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000008;
            }
            serverNames_.add(s);
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
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        serverNames_ = serverNames_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.cse535.proto.Linearpbft.internal_static_TransactionInputConfig_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.cse535.proto.Linearpbft.internal_static_TransactionInputConfig_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.cse535.proto.TransactionInputConfig.class, org.cse535.proto.TransactionInputConfig.Builder.class);
  }

  private int bitField0_;
  public static final int SETNUMBER_FIELD_NUMBER = 1;
  private int setNumber_;
  /**
   * <code>int32 setNumber = 1;</code>
   */
  public int getSetNumber() {
    return setNumber_;
  }

  public static final int TRANSACTION_FIELD_NUMBER = 2;
  private org.cse535.proto.Transaction transaction_;
  /**
   * <code>.Transaction transaction = 2;</code>
   */
  public boolean hasTransaction() {
    return transaction_ != null;
  }
  /**
   * <code>.Transaction transaction = 2;</code>
   */
  public org.cse535.proto.Transaction getTransaction() {
    return transaction_ == null ? org.cse535.proto.Transaction.getDefaultInstance() : transaction_;
  }
  /**
   * <code>.Transaction transaction = 2;</code>
   */
  public org.cse535.proto.TransactionOrBuilder getTransactionOrBuilder() {
    return getTransaction();
  }

  public static final int VIEW_FIELD_NUMBER = 3;
  private int view_;
  /**
   * <code>int32 view = 3;</code>
   */
  public int getView() {
    return view_;
  }

  public static final int SERVERNAMES_FIELD_NUMBER = 5;
  private com.google.protobuf.LazyStringList serverNames_;
  /**
   * <code>repeated string serverNames = 5;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getServerNamesList() {
    return serverNames_;
  }
  /**
   * <code>repeated string serverNames = 5;</code>
   */
  public int getServerNamesCount() {
    return serverNames_.size();
  }
  /**
   * <code>repeated string serverNames = 5;</code>
   */
  public java.lang.String getServerNames(int index) {
    return serverNames_.get(index);
  }
  /**
   * <code>repeated string serverNames = 5;</code>
   */
  public com.google.protobuf.ByteString
      getServerNamesBytes(int index) {
    return serverNames_.getByteString(index);
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
    if (setNumber_ != 0) {
      output.writeInt32(1, setNumber_);
    }
    if (transaction_ != null) {
      output.writeMessage(2, getTransaction());
    }
    if (view_ != 0) {
      output.writeInt32(3, view_);
    }
    for (int i = 0; i < serverNames_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, serverNames_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (setNumber_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, setNumber_);
    }
    if (transaction_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getTransaction());
    }
    if (view_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, view_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < serverNames_.size(); i++) {
        dataSize += computeStringSizeNoTag(serverNames_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getServerNamesList().size();
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
    if (!(obj instanceof org.cse535.proto.TransactionInputConfig)) {
      return super.equals(obj);
    }
    org.cse535.proto.TransactionInputConfig other = (org.cse535.proto.TransactionInputConfig) obj;

    boolean result = true;
    result = result && (getSetNumber()
        == other.getSetNumber());
    result = result && (hasTransaction() == other.hasTransaction());
    if (hasTransaction()) {
      result = result && getTransaction()
          .equals(other.getTransaction());
    }
    result = result && (getView()
        == other.getView());
    result = result && getServerNamesList()
        .equals(other.getServerNamesList());
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
    hash = (37 * hash) + SETNUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getSetNumber();
    if (hasTransaction()) {
      hash = (37 * hash) + TRANSACTION_FIELD_NUMBER;
      hash = (53 * hash) + getTransaction().hashCode();
    }
    hash = (37 * hash) + VIEW_FIELD_NUMBER;
    hash = (53 * hash) + getView();
    if (getServerNamesCount() > 0) {
      hash = (37 * hash) + SERVERNAMES_FIELD_NUMBER;
      hash = (53 * hash) + getServerNamesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.cse535.proto.TransactionInputConfig parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.cse535.proto.TransactionInputConfig parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.cse535.proto.TransactionInputConfig parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.cse535.proto.TransactionInputConfig parseFrom(
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
  public static Builder newBuilder(org.cse535.proto.TransactionInputConfig prototype) {
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
   * Protobuf type {@code TransactionInputConfig}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TransactionInputConfig)
      org.cse535.proto.TransactionInputConfigOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.cse535.proto.Linearpbft.internal_static_TransactionInputConfig_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.cse535.proto.Linearpbft.internal_static_TransactionInputConfig_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.cse535.proto.TransactionInputConfig.class, org.cse535.proto.TransactionInputConfig.Builder.class);
    }

    // Construct using org.cse535.proto.TransactionInputConfig.newBuilder()
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
      setNumber_ = 0;

      if (transactionBuilder_ == null) {
        transaction_ = null;
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }
      view_ = 0;

      serverNames_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.cse535.proto.Linearpbft.internal_static_TransactionInputConfig_descriptor;
    }

    @java.lang.Override
    public org.cse535.proto.TransactionInputConfig getDefaultInstanceForType() {
      return org.cse535.proto.TransactionInputConfig.getDefaultInstance();
    }

    @java.lang.Override
    public org.cse535.proto.TransactionInputConfig build() {
      org.cse535.proto.TransactionInputConfig result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.cse535.proto.TransactionInputConfig buildPartial() {
      org.cse535.proto.TransactionInputConfig result = new org.cse535.proto.TransactionInputConfig(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.setNumber_ = setNumber_;
      if (transactionBuilder_ == null) {
        result.transaction_ = transaction_;
      } else {
        result.transaction_ = transactionBuilder_.build();
      }
      result.view_ = view_;
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        serverNames_ = serverNames_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.serverNames_ = serverNames_;
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof org.cse535.proto.TransactionInputConfig) {
        return mergeFrom((org.cse535.proto.TransactionInputConfig)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.cse535.proto.TransactionInputConfig other) {
      if (other == org.cse535.proto.TransactionInputConfig.getDefaultInstance()) return this;
      if (other.getSetNumber() != 0) {
        setSetNumber(other.getSetNumber());
      }
      if (other.hasTransaction()) {
        mergeTransaction(other.getTransaction());
      }
      if (other.getView() != 0) {
        setView(other.getView());
      }
      if (!other.serverNames_.isEmpty()) {
        if (serverNames_.isEmpty()) {
          serverNames_ = other.serverNames_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensureServerNamesIsMutable();
          serverNames_.addAll(other.serverNames_);
        }
        onChanged();
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
      org.cse535.proto.TransactionInputConfig parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.cse535.proto.TransactionInputConfig) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int setNumber_ ;
    /**
     * <code>int32 setNumber = 1;</code>
     */
    public int getSetNumber() {
      return setNumber_;
    }
    /**
     * <code>int32 setNumber = 1;</code>
     */
    public Builder setSetNumber(int value) {
      
      setNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 setNumber = 1;</code>
     */
    public Builder clearSetNumber() {
      
      setNumber_ = 0;
      onChanged();
      return this;
    }

    private org.cse535.proto.Transaction transaction_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.cse535.proto.Transaction, org.cse535.proto.Transaction.Builder, org.cse535.proto.TransactionOrBuilder> transactionBuilder_;
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public boolean hasTransaction() {
      return transactionBuilder_ != null || transaction_ != null;
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public org.cse535.proto.Transaction getTransaction() {
      if (transactionBuilder_ == null) {
        return transaction_ == null ? org.cse535.proto.Transaction.getDefaultInstance() : transaction_;
      } else {
        return transactionBuilder_.getMessage();
      }
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public Builder setTransaction(org.cse535.proto.Transaction value) {
      if (transactionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        transaction_ = value;
        onChanged();
      } else {
        transactionBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public Builder setTransaction(
        org.cse535.proto.Transaction.Builder builderForValue) {
      if (transactionBuilder_ == null) {
        transaction_ = builderForValue.build();
        onChanged();
      } else {
        transactionBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public Builder mergeTransaction(org.cse535.proto.Transaction value) {
      if (transactionBuilder_ == null) {
        if (transaction_ != null) {
          transaction_ =
            org.cse535.proto.Transaction.newBuilder(transaction_).mergeFrom(value).buildPartial();
        } else {
          transaction_ = value;
        }
        onChanged();
      } else {
        transactionBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public Builder clearTransaction() {
      if (transactionBuilder_ == null) {
        transaction_ = null;
        onChanged();
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public org.cse535.proto.Transaction.Builder getTransactionBuilder() {
      
      onChanged();
      return getTransactionFieldBuilder().getBuilder();
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    public org.cse535.proto.TransactionOrBuilder getTransactionOrBuilder() {
      if (transactionBuilder_ != null) {
        return transactionBuilder_.getMessageOrBuilder();
      } else {
        return transaction_ == null ?
            org.cse535.proto.Transaction.getDefaultInstance() : transaction_;
      }
    }
    /**
     * <code>.Transaction transaction = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.cse535.proto.Transaction, org.cse535.proto.Transaction.Builder, org.cse535.proto.TransactionOrBuilder> 
        getTransactionFieldBuilder() {
      if (transactionBuilder_ == null) {
        transactionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.cse535.proto.Transaction, org.cse535.proto.Transaction.Builder, org.cse535.proto.TransactionOrBuilder>(
                getTransaction(),
                getParentForChildren(),
                isClean());
        transaction_ = null;
      }
      return transactionBuilder_;
    }

    private int view_ ;
    /**
     * <code>int32 view = 3;</code>
     */
    public int getView() {
      return view_;
    }
    /**
     * <code>int32 view = 3;</code>
     */
    public Builder setView(int value) {
      
      view_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 view = 3;</code>
     */
    public Builder clearView() {
      
      view_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList serverNames_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureServerNamesIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        serverNames_ = new com.google.protobuf.LazyStringArrayList(serverNames_);
        bitField0_ |= 0x00000008;
       }
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getServerNamesList() {
      return serverNames_.getUnmodifiableView();
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public int getServerNamesCount() {
      return serverNames_.size();
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public java.lang.String getServerNames(int index) {
      return serverNames_.get(index);
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public com.google.protobuf.ByteString
        getServerNamesBytes(int index) {
      return serverNames_.getByteString(index);
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public Builder setServerNames(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureServerNamesIsMutable();
      serverNames_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public Builder addServerNames(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureServerNamesIsMutable();
      serverNames_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public Builder addAllServerNames(
        java.lang.Iterable<java.lang.String> values) {
      ensureServerNamesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, serverNames_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public Builder clearServerNames() {
      serverNames_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string serverNames = 5;</code>
     */
    public Builder addServerNamesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureServerNamesIsMutable();
      serverNames_.add(value);
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:TransactionInputConfig)
  }

  // @@protoc_insertion_point(class_scope:TransactionInputConfig)
  private static final org.cse535.proto.TransactionInputConfig DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.cse535.proto.TransactionInputConfig();
  }

  public static org.cse535.proto.TransactionInputConfig getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransactionInputConfig>
      PARSER = new com.google.protobuf.AbstractParser<TransactionInputConfig>() {
    @java.lang.Override
    public TransactionInputConfig parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TransactionInputConfig(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransactionInputConfig> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransactionInputConfig> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.cse535.proto.TransactionInputConfig getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

