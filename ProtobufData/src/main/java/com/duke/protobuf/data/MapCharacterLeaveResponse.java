// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

/**
 * Protobuf type {@code com.duke.protobuf.data.MapCharacterLeaveResponse}
 */
public final class MapCharacterLeaveResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.protobuf.data.MapCharacterLeaveResponse)
    MapCharacterLeaveResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MapCharacterLeaveResponse.newBuilder() to construct.
  private MapCharacterLeaveResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MapCharacterLeaveResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MapCharacterLeaveResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MapCharacterLeaveResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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

            entityId_ = input.readInt32();
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
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
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
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapCharacterLeaveResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapCharacterLeaveResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.protobuf.data.MapCharacterLeaveResponse.class, com.duke.protobuf.data.MapCharacterLeaveResponse.Builder.class);
  }

  public static final int ENTITYID_FIELD_NUMBER = 1;
  private int entityId_;
  /**
   * <code>int32 entityId = 1;</code>
   * @return The entityId.
   */
  @java.lang.Override
  public int getEntityId() {
    return entityId_;
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
    if (entityId_ != 0) {
      output.writeInt32(1, entityId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (entityId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, entityId_);
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
    if (!(obj instanceof com.duke.protobuf.data.MapCharacterLeaveResponse)) {
      return super.equals(obj);
    }
    com.duke.protobuf.data.MapCharacterLeaveResponse other = (com.duke.protobuf.data.MapCharacterLeaveResponse) obj;

    if (getEntityId()
        != other.getEntityId()) return false;
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
    hash = (37 * hash) + ENTITYID_FIELD_NUMBER;
    hash = (53 * hash) + getEntityId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.MapCharacterLeaveResponse parseFrom(
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
  public static Builder newBuilder(com.duke.protobuf.data.MapCharacterLeaveResponse prototype) {
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
   * Protobuf type {@code com.duke.protobuf.data.MapCharacterLeaveResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.protobuf.data.MapCharacterLeaveResponse)
      com.duke.protobuf.data.MapCharacterLeaveResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapCharacterLeaveResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapCharacterLeaveResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.protobuf.data.MapCharacterLeaveResponse.class, com.duke.protobuf.data.MapCharacterLeaveResponse.Builder.class);
    }

    // Construct using com.duke.protobuf.data.MapCharacterLeaveResponse.newBuilder()
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
      entityId_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_MapCharacterLeaveResponse_descriptor;
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapCharacterLeaveResponse getDefaultInstanceForType() {
      return com.duke.protobuf.data.MapCharacterLeaveResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapCharacterLeaveResponse build() {
      com.duke.protobuf.data.MapCharacterLeaveResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.protobuf.data.MapCharacterLeaveResponse buildPartial() {
      com.duke.protobuf.data.MapCharacterLeaveResponse result = new com.duke.protobuf.data.MapCharacterLeaveResponse(this);
      result.entityId_ = entityId_;
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
      if (other instanceof com.duke.protobuf.data.MapCharacterLeaveResponse) {
        return mergeFrom((com.duke.protobuf.data.MapCharacterLeaveResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.protobuf.data.MapCharacterLeaveResponse other) {
      if (other == com.duke.protobuf.data.MapCharacterLeaveResponse.getDefaultInstance()) return this;
      if (other.getEntityId() != 0) {
        setEntityId(other.getEntityId());
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
      com.duke.protobuf.data.MapCharacterLeaveResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.protobuf.data.MapCharacterLeaveResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int entityId_ ;
    /**
     * <code>int32 entityId = 1;</code>
     * @return The entityId.
     */
    @java.lang.Override
    public int getEntityId() {
      return entityId_;
    }
    /**
     * <code>int32 entityId = 1;</code>
     * @param value The entityId to set.
     * @return This builder for chaining.
     */
    public Builder setEntityId(int value) {
      
      entityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 entityId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEntityId() {
      
      entityId_ = 0;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:com.duke.protobuf.data.MapCharacterLeaveResponse)
  }

  // @@protoc_insertion_point(class_scope:com.duke.protobuf.data.MapCharacterLeaveResponse)
  private static final com.duke.protobuf.data.MapCharacterLeaveResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.protobuf.data.MapCharacterLeaveResponse();
  }

  public static com.duke.protobuf.data.MapCharacterLeaveResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MapCharacterLeaveResponse>
      PARSER = new com.google.protobuf.AbstractParser<MapCharacterLeaveResponse>() {
    @java.lang.Override
    public MapCharacterLeaveResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MapCharacterLeaveResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MapCharacterLeaveResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MapCharacterLeaveResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.protobuf.data.MapCharacterLeaveResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

