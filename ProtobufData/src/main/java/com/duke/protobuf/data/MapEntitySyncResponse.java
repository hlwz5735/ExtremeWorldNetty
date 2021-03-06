// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.protobuf.data;

/**
 * Protobuf type {@code com.duke.protobuf.data.MapEntitySyncResponse}
 */
public final class MapEntitySyncResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.protobuf.data.MapEntitySyncResponse)
    MapEntitySyncResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MapEntitySyncResponse.newBuilder() to construct.
  private MapEntitySyncResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MapEntitySyncResponse() {
    entitySyncs_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MapEntitySyncResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MapEntitySyncResponse(
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
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              entitySyncs_ = new java.util.ArrayList<com.duke.protobuf.data.NEntitySync>();
              mutable_bitField0_ |= 0x00000001;
            }
            entitySyncs_.add(
                input.readMessage(com.duke.protobuf.data.NEntitySync.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        entitySyncs_ = java.util.Collections.unmodifiableList(entitySyncs_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapEntitySyncResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapEntitySyncResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.protobuf.data.MapEntitySyncResponse.class, com.duke.protobuf.data.MapEntitySyncResponse.Builder.class);
  }

  public static final int ENTITYSYNCS_FIELD_NUMBER = 2;
  private java.util.List<com.duke.protobuf.data.NEntitySync> entitySyncs_;
  /**
   * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.duke.protobuf.data.NEntitySync> getEntitySyncsList() {
    return entitySyncs_;
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.duke.protobuf.data.NEntitySyncOrBuilder> 
      getEntitySyncsOrBuilderList() {
    return entitySyncs_;
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
   */
  @java.lang.Override
  public int getEntitySyncsCount() {
    return entitySyncs_.size();
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NEntitySync getEntitySyncs(int index) {
    return entitySyncs_.get(index);
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NEntitySyncOrBuilder getEntitySyncsOrBuilder(
      int index) {
    return entitySyncs_.get(index);
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
    for (int i = 0; i < entitySyncs_.size(); i++) {
      output.writeMessage(2, entitySyncs_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < entitySyncs_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, entitySyncs_.get(i));
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
    if (!(obj instanceof com.duke.protobuf.data.MapEntitySyncResponse)) {
      return super.equals(obj);
    }
    com.duke.protobuf.data.MapEntitySyncResponse other = (com.duke.protobuf.data.MapEntitySyncResponse) obj;

    if (!getEntitySyncsList()
        .equals(other.getEntitySyncsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getEntitySyncsCount() > 0) {
      hash = (37 * hash) + ENTITYSYNCS_FIELD_NUMBER;
      hash = (53 * hash) + getEntitySyncsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapEntitySyncResponse parseFrom(
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
  public static Builder newBuilder(com.duke.protobuf.data.MapEntitySyncResponse prototype) {
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
   * Protobuf type {@code com.duke.protobuf.data.MapEntitySyncResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.protobuf.data.MapEntitySyncResponse)
      com.duke.protobuf.data.MapEntitySyncResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapEntitySyncResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapEntitySyncResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.protobuf.data.MapEntitySyncResponse.class, com.duke.protobuf.data.MapEntitySyncResponse.Builder.class);
    }

    // Construct using com.duke.protobuf.data.MapEntitySyncResponse.newBuilder()
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
        getEntitySyncsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (entitySyncsBuilder_ == null) {
        entitySyncs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        entitySyncsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapEntitySyncResponse_descriptor;
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapEntitySyncResponse getDefaultInstanceForType() {
      return com.duke.protobuf.data.MapEntitySyncResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapEntitySyncResponse build() {
      com.duke.protobuf.data.MapEntitySyncResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapEntitySyncResponse buildPartial() {
      com.duke.protobuf.data.MapEntitySyncResponse result = new com.duke.protobuf.data.MapEntitySyncResponse(this);
      int from_bitField0_ = bitField0_;
      if (entitySyncsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          entitySyncs_ = java.util.Collections.unmodifiableList(entitySyncs_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.entitySyncs_ = entitySyncs_;
      } else {
        result.entitySyncs_ = entitySyncsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.duke.protobuf.data.MapEntitySyncResponse) {
        return mergeFrom((com.duke.protobuf.data.MapEntitySyncResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.protobuf.data.MapEntitySyncResponse other) {
      if (other == com.duke.protobuf.data.MapEntitySyncResponse.getDefaultInstance()) return this;
      if (entitySyncsBuilder_ == null) {
        if (!other.entitySyncs_.isEmpty()) {
          if (entitySyncs_.isEmpty()) {
            entitySyncs_ = other.entitySyncs_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureEntitySyncsIsMutable();
            entitySyncs_.addAll(other.entitySyncs_);
          }
          onChanged();
        }
      } else {
        if (!other.entitySyncs_.isEmpty()) {
          if (entitySyncsBuilder_.isEmpty()) {
            entitySyncsBuilder_.dispose();
            entitySyncsBuilder_ = null;
            entitySyncs_ = other.entitySyncs_;
            bitField0_ = (bitField0_ & ~0x00000001);
            entitySyncsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEntitySyncsFieldBuilder() : null;
          } else {
            entitySyncsBuilder_.addAllMessages(other.entitySyncs_);
          }
        }
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
      com.duke.protobuf.data.MapEntitySyncResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.protobuf.data.MapEntitySyncResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.duke.protobuf.data.NEntitySync> entitySyncs_ =
      java.util.Collections.emptyList();
    private void ensureEntitySyncsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        entitySyncs_ = new java.util.ArrayList<com.duke.protobuf.data.NEntitySync>(entitySyncs_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.duke.protobuf.data.NEntitySync, com.duke.protobuf.data.NEntitySync.Builder, com.duke.protobuf.data.NEntitySyncOrBuilder> entitySyncsBuilder_;

    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public java.util.List<com.duke.protobuf.data.NEntitySync> getEntitySyncsList() {
      if (entitySyncsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(entitySyncs_);
      } else {
        return entitySyncsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public int getEntitySyncsCount() {
      if (entitySyncsBuilder_ == null) {
        return entitySyncs_.size();
      } else {
        return entitySyncsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public com.duke.protobuf.data.NEntitySync getEntitySyncs(int index) {
      if (entitySyncsBuilder_ == null) {
        return entitySyncs_.get(index);
      } else {
        return entitySyncsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder setEntitySyncs(
        int index, com.duke.protobuf.data.NEntitySync value) {
      if (entitySyncsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntitySyncsIsMutable();
        entitySyncs_.set(index, value);
        onChanged();
      } else {
        entitySyncsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder setEntitySyncs(
        int index, com.duke.protobuf.data.NEntitySync.Builder builderForValue) {
      if (entitySyncsBuilder_ == null) {
        ensureEntitySyncsIsMutable();
        entitySyncs_.set(index, builderForValue.build());
        onChanged();
      } else {
        entitySyncsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder addEntitySyncs(com.duke.protobuf.data.NEntitySync value) {
      if (entitySyncsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntitySyncsIsMutable();
        entitySyncs_.add(value);
        onChanged();
      } else {
        entitySyncsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder addEntitySyncs(
        int index, com.duke.protobuf.data.NEntitySync value) {
      if (entitySyncsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntitySyncsIsMutable();
        entitySyncs_.add(index, value);
        onChanged();
      } else {
        entitySyncsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder addEntitySyncs(
        com.duke.protobuf.data.NEntitySync.Builder builderForValue) {
      if (entitySyncsBuilder_ == null) {
        ensureEntitySyncsIsMutable();
        entitySyncs_.add(builderForValue.build());
        onChanged();
      } else {
        entitySyncsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder addEntitySyncs(
        int index, com.duke.protobuf.data.NEntitySync.Builder builderForValue) {
      if (entitySyncsBuilder_ == null) {
        ensureEntitySyncsIsMutable();
        entitySyncs_.add(index, builderForValue.build());
        onChanged();
      } else {
        entitySyncsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder addAllEntitySyncs(
        java.lang.Iterable<? extends com.duke.protobuf.data.NEntitySync> values) {
      if (entitySyncsBuilder_ == null) {
        ensureEntitySyncsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, entitySyncs_);
        onChanged();
      } else {
        entitySyncsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder clearEntitySyncs() {
      if (entitySyncsBuilder_ == null) {
        entitySyncs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        entitySyncsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public Builder removeEntitySyncs(int index) {
      if (entitySyncsBuilder_ == null) {
        ensureEntitySyncsIsMutable();
        entitySyncs_.remove(index);
        onChanged();
      } else {
        entitySyncsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public com.duke.protobuf.data.NEntitySync.Builder getEntitySyncsBuilder(
        int index) {
      return getEntitySyncsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public com.duke.protobuf.data.NEntitySyncOrBuilder getEntitySyncsOrBuilder(
        int index) {
      if (entitySyncsBuilder_ == null) {
        return entitySyncs_.get(index);  } else {
        return entitySyncsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public java.util.List<? extends com.duke.protobuf.data.NEntitySyncOrBuilder> 
         getEntitySyncsOrBuilderList() {
      if (entitySyncsBuilder_ != null) {
        return entitySyncsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(entitySyncs_);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public com.duke.protobuf.data.NEntitySync.Builder addEntitySyncsBuilder() {
      return getEntitySyncsFieldBuilder().addBuilder(
          com.duke.protobuf.data.NEntitySync.getDefaultInstance());
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public com.duke.protobuf.data.NEntitySync.Builder addEntitySyncsBuilder(
        int index) {
      return getEntitySyncsFieldBuilder().addBuilder(
          index, com.duke.protobuf.data.NEntitySync.getDefaultInstance());
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NEntitySync entitySyncs = 2;</code>
     */
    public java.util.List<com.duke.protobuf.data.NEntitySync.Builder> 
         getEntitySyncsBuilderList() {
      return getEntitySyncsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.duke.protobuf.data.NEntitySync, com.duke.protobuf.data.NEntitySync.Builder, com.duke.protobuf.data.NEntitySyncOrBuilder> 
        getEntitySyncsFieldBuilder() {
      if (entitySyncsBuilder_ == null) {
        entitySyncsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.duke.protobuf.data.NEntitySync, com.duke.protobuf.data.NEntitySync.Builder, com.duke.protobuf.data.NEntitySyncOrBuilder>(
                entitySyncs_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        entitySyncs_ = null;
      }
      return entitySyncsBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.duke.protobuf.data.MapEntitySyncResponse)
  }

  // @@protoc_insertion_point(class_scope:com.duke.protobuf.data.MapEntitySyncResponse)
  private static final com.duke.protobuf.data.MapEntitySyncResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.protobuf.data.MapEntitySyncResponse();
  }

  public static com.duke.protobuf.data.MapEntitySyncResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MapEntitySyncResponse>
      PARSER = new com.google.protobuf.AbstractParser<MapEntitySyncResponse>() {
    @java.lang.Override
    public MapEntitySyncResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MapEntitySyncResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MapEntitySyncResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MapEntitySyncResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.protobuf.data.MapEntitySyncResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

