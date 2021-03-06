// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.protobuf.data;

/**
 * Protobuf type {@code com.duke.protobuf.data.NetMessage}
 */
public final class NetMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.protobuf.data.NetMessage)
    NetMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NetMessage.newBuilder() to construct.
  private NetMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NetMessage() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new NetMessage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NetMessage(
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
          case 10: {
            com.duke.protobuf.data.NetMessageRequest.Builder subBuilder = null;
            if (request_ != null) {
              subBuilder = request_.toBuilder();
            }
            request_ = input.readMessage(com.duke.protobuf.data.NetMessageRequest.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(request_);
              request_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.duke.protobuf.data.NetMessageResponse.Builder subBuilder = null;
            if (response_ != null) {
              subBuilder = response_.toBuilder();
            }
            response_ = input.readMessage(com.duke.protobuf.data.NetMessageResponse.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(response_);
              response_ = subBuilder.buildPartial();
            }

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
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NetMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NetMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.protobuf.data.NetMessage.class, com.duke.protobuf.data.NetMessage.Builder.class);
  }

  public static final int REQUEST_FIELD_NUMBER = 1;
  private com.duke.protobuf.data.NetMessageRequest request_;
  /**
   * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
   * @return Whether the request field is set.
   */
  @java.lang.Override
  public boolean hasRequest() {
    return request_ != null;
  }
  /**
   * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
   * @return The request.
   */
  @java.lang.Override
  public com.duke.protobuf.data.NetMessageRequest getRequest() {
    return request_ == null ? com.duke.protobuf.data.NetMessageRequest.getDefaultInstance() : request_;
  }
  /**
   * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NetMessageRequestOrBuilder getRequestOrBuilder() {
    return getRequest();
  }

  public static final int RESPONSE_FIELD_NUMBER = 2;
  private com.duke.protobuf.data.NetMessageResponse response_;
  /**
   * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
   * @return Whether the response field is set.
   */
  @java.lang.Override
  public boolean hasResponse() {
    return response_ != null;
  }
  /**
   * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
   * @return The response.
   */
  @java.lang.Override
  public com.duke.protobuf.data.NetMessageResponse getResponse() {
    return response_ == null ? com.duke.protobuf.data.NetMessageResponse.getDefaultInstance() : response_;
  }
  /**
   * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NetMessageResponseOrBuilder getResponseOrBuilder() {
    return getResponse();
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
    if (request_ != null) {
      output.writeMessage(1, getRequest());
    }
    if (response_ != null) {
      output.writeMessage(2, getResponse());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (request_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRequest());
    }
    if (response_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getResponse());
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
    if (!(obj instanceof com.duke.protobuf.data.NetMessage)) {
      return super.equals(obj);
    }
    com.duke.protobuf.data.NetMessage other = (com.duke.protobuf.data.NetMessage) obj;

    if (hasRequest() != other.hasRequest()) return false;
    if (hasRequest()) {
      if (!getRequest()
          .equals(other.getRequest())) return false;
    }
    if (hasResponse() != other.hasResponse()) return false;
    if (hasResponse()) {
      if (!getResponse()
          .equals(other.getResponse())) return false;
    }
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
    if (hasRequest()) {
      hash = (37 * hash) + REQUEST_FIELD_NUMBER;
      hash = (53 * hash) + getRequest().hashCode();
    }
    if (hasResponse()) {
      hash = (37 * hash) + RESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getResponse().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.protobuf.data.NetMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.NetMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NetMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NetMessage parseFrom(
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
  public static Builder newBuilder(com.duke.protobuf.data.NetMessage prototype) {
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
   * Protobuf type {@code com.duke.protobuf.data.NetMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.protobuf.data.NetMessage)
      com.duke.protobuf.data.NetMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NetMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NetMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.protobuf.data.NetMessage.class, com.duke.protobuf.data.NetMessage.Builder.class);
    }

    // Construct using com.duke.protobuf.data.NetMessage.newBuilder()
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
      if (requestBuilder_ == null) {
        request_ = null;
      } else {
        request_ = null;
        requestBuilder_ = null;
      }
      if (responseBuilder_ == null) {
        response_ = null;
      } else {
        response_ = null;
        responseBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NetMessage_descriptor;
    }

    @java.lang.Override
    public com.duke.protobuf.data.NetMessage getDefaultInstanceForType() {
      return com.duke.protobuf.data.NetMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.protobuf.data.NetMessage build() {
      com.duke.protobuf.data.NetMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.protobuf.data.NetMessage buildPartial() {
      com.duke.protobuf.data.NetMessage result = new com.duke.protobuf.data.NetMessage(this);
      if (requestBuilder_ == null) {
        result.request_ = request_;
      } else {
        result.request_ = requestBuilder_.build();
      }
      if (responseBuilder_ == null) {
        result.response_ = response_;
      } else {
        result.response_ = responseBuilder_.build();
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
      if (other instanceof com.duke.protobuf.data.NetMessage) {
        return mergeFrom((com.duke.protobuf.data.NetMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.protobuf.data.NetMessage other) {
      if (other == com.duke.protobuf.data.NetMessage.getDefaultInstance()) return this;
      if (other.hasRequest()) {
        mergeRequest(other.getRequest());
      }
      if (other.hasResponse()) {
        mergeResponse(other.getResponse());
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
      com.duke.protobuf.data.NetMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.protobuf.data.NetMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.duke.protobuf.data.NetMessageRequest request_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NetMessageRequest, com.duke.protobuf.data.NetMessageRequest.Builder, com.duke.protobuf.data.NetMessageRequestOrBuilder> requestBuilder_;
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     * @return Whether the request field is set.
     */
    public boolean hasRequest() {
      return requestBuilder_ != null || request_ != null;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     * @return The request.
     */
    public com.duke.protobuf.data.NetMessageRequest getRequest() {
      if (requestBuilder_ == null) {
        return request_ == null ? com.duke.protobuf.data.NetMessageRequest.getDefaultInstance() : request_;
      } else {
        return requestBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public Builder setRequest(com.duke.protobuf.data.NetMessageRequest value) {
      if (requestBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        request_ = value;
        onChanged();
      } else {
        requestBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public Builder setRequest(
        com.duke.protobuf.data.NetMessageRequest.Builder builderForValue) {
      if (requestBuilder_ == null) {
        request_ = builderForValue.build();
        onChanged();
      } else {
        requestBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public Builder mergeRequest(com.duke.protobuf.data.NetMessageRequest value) {
      if (requestBuilder_ == null) {
        if (request_ != null) {
          request_ =
            com.duke.protobuf.data.NetMessageRequest.newBuilder(request_).mergeFrom(value).buildPartial();
        } else {
          request_ = value;
        }
        onChanged();
      } else {
        requestBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public Builder clearRequest() {
      if (requestBuilder_ == null) {
        request_ = null;
        onChanged();
      } else {
        request_ = null;
        requestBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public com.duke.protobuf.data.NetMessageRequest.Builder getRequestBuilder() {
      
      onChanged();
      return getRequestFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    public com.duke.protobuf.data.NetMessageRequestOrBuilder getRequestOrBuilder() {
      if (requestBuilder_ != null) {
        return requestBuilder_.getMessageOrBuilder();
      } else {
        return request_ == null ?
            com.duke.protobuf.data.NetMessageRequest.getDefaultInstance() : request_;
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageRequest Request = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NetMessageRequest, com.duke.protobuf.data.NetMessageRequest.Builder, com.duke.protobuf.data.NetMessageRequestOrBuilder> 
        getRequestFieldBuilder() {
      if (requestBuilder_ == null) {
        requestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.duke.protobuf.data.NetMessageRequest, com.duke.protobuf.data.NetMessageRequest.Builder, com.duke.protobuf.data.NetMessageRequestOrBuilder>(
                getRequest(),
                getParentForChildren(),
                isClean());
        request_ = null;
      }
      return requestBuilder_;
    }

    private com.duke.protobuf.data.NetMessageResponse response_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NetMessageResponse, com.duke.protobuf.data.NetMessageResponse.Builder, com.duke.protobuf.data.NetMessageResponseOrBuilder> responseBuilder_;
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     * @return Whether the response field is set.
     */
    public boolean hasResponse() {
      return responseBuilder_ != null || response_ != null;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     * @return The response.
     */
    public com.duke.protobuf.data.NetMessageResponse getResponse() {
      if (responseBuilder_ == null) {
        return response_ == null ? com.duke.protobuf.data.NetMessageResponse.getDefaultInstance() : response_;
      } else {
        return responseBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public Builder setResponse(com.duke.protobuf.data.NetMessageResponse value) {
      if (responseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        response_ = value;
        onChanged();
      } else {
        responseBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public Builder setResponse(
        com.duke.protobuf.data.NetMessageResponse.Builder builderForValue) {
      if (responseBuilder_ == null) {
        response_ = builderForValue.build();
        onChanged();
      } else {
        responseBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public Builder mergeResponse(com.duke.protobuf.data.NetMessageResponse value) {
      if (responseBuilder_ == null) {
        if (response_ != null) {
          response_ =
            com.duke.protobuf.data.NetMessageResponse.newBuilder(response_).mergeFrom(value).buildPartial();
        } else {
          response_ = value;
        }
        onChanged();
      } else {
        responseBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public Builder clearResponse() {
      if (responseBuilder_ == null) {
        response_ = null;
        onChanged();
      } else {
        response_ = null;
        responseBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public com.duke.protobuf.data.NetMessageResponse.Builder getResponseBuilder() {
      
      onChanged();
      return getResponseFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    public com.duke.protobuf.data.NetMessageResponseOrBuilder getResponseOrBuilder() {
      if (responseBuilder_ != null) {
        return responseBuilder_.getMessageOrBuilder();
      } else {
        return response_ == null ?
            com.duke.protobuf.data.NetMessageResponse.getDefaultInstance() : response_;
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NetMessageResponse Response = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NetMessageResponse, com.duke.protobuf.data.NetMessageResponse.Builder, com.duke.protobuf.data.NetMessageResponseOrBuilder> 
        getResponseFieldBuilder() {
      if (responseBuilder_ == null) {
        responseBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.duke.protobuf.data.NetMessageResponse, com.duke.protobuf.data.NetMessageResponse.Builder, com.duke.protobuf.data.NetMessageResponseOrBuilder>(
                getResponse(),
                getParentForChildren(),
                isClean());
        response_ = null;
      }
      return responseBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.duke.protobuf.data.NetMessage)
  }

  // @@protoc_insertion_point(class_scope:com.duke.protobuf.data.NetMessage)
  private static final com.duke.protobuf.data.NetMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.protobuf.data.NetMessage();
  }

  public static com.duke.protobuf.data.NetMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NetMessage>
      PARSER = new com.google.protobuf.AbstractParser<NetMessage>() {
    @java.lang.Override
    public NetMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NetMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NetMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NetMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.protobuf.data.NetMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

