// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.proto.data;

/**
 * Protobuf type {@code com.duke.proto.data.UserRegisterResponse}
 */
public final class UserRegisterResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.proto.data.UserRegisterResponse)
    UserRegisterResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserRegisterResponse.newBuilder() to construct.
  private UserRegisterResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserRegisterResponse() {
    result_ = 0;
    errormsg_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UserRegisterResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserRegisterResponse(
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
            int rawValue = input.readEnum();

            result_ = rawValue;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            errormsg_ = s;
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.duke.proto.data.Message.internal_static_com_duke_proto_data_UserRegisterResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.proto.data.Message.internal_static_com_duke_proto_data_UserRegisterResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.proto.data.UserRegisterResponse.class, com.duke.proto.data.UserRegisterResponse.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 1;
  private int result_;
  /**
   * <code>.com.duke.proto.data.RESULT result = 1;</code>
   * @return The enum numeric value on the wire for result.
   */
  @java.lang.Override public int getResultValue() {
    return result_;
  }
  /**
   * <code>.com.duke.proto.data.RESULT result = 1;</code>
   * @return The result.
   */
  @java.lang.Override public com.duke.proto.data.RESULT getResult() {
    @SuppressWarnings("deprecation")
    com.duke.proto.data.RESULT result = com.duke.proto.data.RESULT.valueOf(result_);
    return result == null ? com.duke.proto.data.RESULT.UNRECOGNIZED : result;
  }

  public static final int ERRORMSG_FIELD_NUMBER = 2;
  private volatile java.lang.Object errormsg_;
  /**
   * <code>string errormsg = 2;</code>
   * @return The errormsg.
   */
  @java.lang.Override
  public java.lang.String getErrormsg() {
    java.lang.Object ref = errormsg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      errormsg_ = s;
      return s;
    }
  }
  /**
   * <code>string errormsg = 2;</code>
   * @return The bytes for errormsg.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getErrormsgBytes() {
    java.lang.Object ref = errormsg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      errormsg_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (result_ != com.duke.proto.data.RESULT.SUCCESS.getNumber()) {
      output.writeEnum(1, result_);
    }
    if (!getErrormsgBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, errormsg_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (result_ != com.duke.proto.data.RESULT.SUCCESS.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, result_);
    }
    if (!getErrormsgBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, errormsg_);
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
    if (!(obj instanceof com.duke.proto.data.UserRegisterResponse)) {
      return super.equals(obj);
    }
    com.duke.proto.data.UserRegisterResponse other = (com.duke.proto.data.UserRegisterResponse) obj;

    if (result_ != other.result_) return false;
    if (!getErrormsg()
        .equals(other.getErrormsg())) return false;
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
    hash = (37 * hash) + RESULT_FIELD_NUMBER;
    hash = (53 * hash) + result_;
    hash = (37 * hash) + ERRORMSG_FIELD_NUMBER;
    hash = (53 * hash) + getErrormsg().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.proto.data.UserRegisterResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.proto.data.UserRegisterResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.proto.data.UserRegisterResponse parseFrom(
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
  public static Builder newBuilder(com.duke.proto.data.UserRegisterResponse prototype) {
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
   * Protobuf type {@code com.duke.proto.data.UserRegisterResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.proto.data.UserRegisterResponse)
      com.duke.proto.data.UserRegisterResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.proto.data.Message.internal_static_com_duke_proto_data_UserRegisterResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.proto.data.Message.internal_static_com_duke_proto_data_UserRegisterResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.proto.data.UserRegisterResponse.class, com.duke.proto.data.UserRegisterResponse.Builder.class);
    }

    // Construct using com.duke.proto.data.UserRegisterResponse.newBuilder()
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
      result_ = 0;

      errormsg_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.proto.data.Message.internal_static_com_duke_proto_data_UserRegisterResponse_descriptor;
    }

    @java.lang.Override
    public com.duke.proto.data.UserRegisterResponse getDefaultInstanceForType() {
      return com.duke.proto.data.UserRegisterResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.proto.data.UserRegisterResponse build() {
      com.duke.proto.data.UserRegisterResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.proto.data.UserRegisterResponse buildPartial() {
      com.duke.proto.data.UserRegisterResponse result = new com.duke.proto.data.UserRegisterResponse(this);
      result.result_ = result_;
      result.errormsg_ = errormsg_;
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
      if (other instanceof com.duke.proto.data.UserRegisterResponse) {
        return mergeFrom((com.duke.proto.data.UserRegisterResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.proto.data.UserRegisterResponse other) {
      if (other == com.duke.proto.data.UserRegisterResponse.getDefaultInstance()) return this;
      if (other.result_ != 0) {
        setResultValue(other.getResultValue());
      }
      if (!other.getErrormsg().isEmpty()) {
        errormsg_ = other.errormsg_;
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
      com.duke.proto.data.UserRegisterResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.proto.data.UserRegisterResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int result_ = 0;
    /**
     * <code>.com.duke.proto.data.RESULT result = 1;</code>
     * @return The enum numeric value on the wire for result.
     */
    @java.lang.Override public int getResultValue() {
      return result_;
    }
    /**
     * <code>.com.duke.proto.data.RESULT result = 1;</code>
     * @param value The enum numeric value on the wire for result to set.
     * @return This builder for chaining.
     */
    public Builder setResultValue(int value) {
      
      result_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.proto.data.RESULT result = 1;</code>
     * @return The result.
     */
    @java.lang.Override
    public com.duke.proto.data.RESULT getResult() {
      @SuppressWarnings("deprecation")
      com.duke.proto.data.RESULT result = com.duke.proto.data.RESULT.valueOf(result_);
      return result == null ? com.duke.proto.data.RESULT.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.duke.proto.data.RESULT result = 1;</code>
     * @param value The result to set.
     * @return This builder for chaining.
     */
    public Builder setResult(com.duke.proto.data.RESULT value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      result_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.proto.data.RESULT result = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearResult() {
      
      result_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object errormsg_ = "";
    /**
     * <code>string errormsg = 2;</code>
     * @return The errormsg.
     */
    public java.lang.String getErrormsg() {
      java.lang.Object ref = errormsg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        errormsg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string errormsg = 2;</code>
     * @return The bytes for errormsg.
     */
    public com.google.protobuf.ByteString
        getErrormsgBytes() {
      java.lang.Object ref = errormsg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errormsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string errormsg = 2;</code>
     * @param value The errormsg to set.
     * @return This builder for chaining.
     */
    public Builder setErrormsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      errormsg_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string errormsg = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearErrormsg() {
      
      errormsg_ = getDefaultInstance().getErrormsg();
      onChanged();
      return this;
    }
    /**
     * <code>string errormsg = 2;</code>
     * @param value The bytes for errormsg to set.
     * @return This builder for chaining.
     */
    public Builder setErrormsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      errormsg_ = value;
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


    // @@protoc_insertion_point(builder_scope:com.duke.proto.data.UserRegisterResponse)
  }

  // @@protoc_insertion_point(class_scope:com.duke.proto.data.UserRegisterResponse)
  private static final com.duke.proto.data.UserRegisterResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.proto.data.UserRegisterResponse();
  }

  public static com.duke.proto.data.UserRegisterResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserRegisterResponse>
      PARSER = new com.google.protobuf.AbstractParser<UserRegisterResponse>() {
    @java.lang.Override
    public UserRegisterResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserRegisterResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserRegisterResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UserRegisterResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.proto.data.UserRegisterResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

