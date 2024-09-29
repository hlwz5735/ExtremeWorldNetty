// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

/**
 * Protobuf type {@code com.duke.protobuf.data.QuestListResponse}
 */
public final class QuestListResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.protobuf.data.QuestListResponse)
    QuestListResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QuestListResponse.newBuilder() to construct.
  private QuestListResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QuestListResponse() {
    result_ = 0;
    errormsg_ = "";
    quests_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QuestListResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QuestListResponse(
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
            int rawValue = input.readEnum();

            result_ = rawValue;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            errormsg_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              quests_ = new java.util.ArrayList<com.duke.protobuf.data.NQuestInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            quests_.add(
                input.readMessage(com.duke.protobuf.data.NQuestInfo.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        quests_ = java.util.Collections.unmodifiableList(quests_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_QuestListResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_QuestListResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.protobuf.data.QuestListResponse.class, com.duke.protobuf.data.QuestListResponse.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 1;
  private int result_;
  /**
   * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
   * @return The enum numeric value on the wire for result.
   */
  @java.lang.Override public int getResultValue() {
    return result_;
  }
  /**
   * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
   * @return The result.
   */
  @java.lang.Override public com.duke.protobuf.data.RESULT getResult() {
    @SuppressWarnings("deprecation")
    com.duke.protobuf.data.RESULT result = com.duke.protobuf.data.RESULT.valueOf(result_);
    return result == null ? com.duke.protobuf.data.RESULT.UNRECOGNIZED : result;
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

  public static final int QUESTS_FIELD_NUMBER = 3;
  private java.util.List<com.duke.protobuf.data.NQuestInfo> quests_;
  /**
   * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.duke.protobuf.data.NQuestInfo> getQuestsList() {
    return quests_;
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.duke.protobuf.data.NQuestInfoOrBuilder> 
      getQuestsOrBuilderList() {
    return quests_;
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
   */
  @java.lang.Override
  public int getQuestsCount() {
    return quests_.size();
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NQuestInfo getQuests(int index) {
    return quests_.get(index);
  }
  /**
   * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NQuestInfoOrBuilder getQuestsOrBuilder(
      int index) {
    return quests_.get(index);
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
    if (result_ != com.duke.protobuf.data.RESULT.SUCCESS.getNumber()) {
      output.writeEnum(1, result_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(errormsg_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, errormsg_);
    }
    for (int i = 0; i < quests_.size(); i++) {
      output.writeMessage(3, quests_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (result_ != com.duke.protobuf.data.RESULT.SUCCESS.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, result_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(errormsg_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, errormsg_);
    }
    for (int i = 0; i < quests_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, quests_.get(i));
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
    if (!(obj instanceof com.duke.protobuf.data.QuestListResponse)) {
      return super.equals(obj);
    }
    com.duke.protobuf.data.QuestListResponse other = (com.duke.protobuf.data.QuestListResponse) obj;

    if (result_ != other.result_) return false;
    if (!getErrormsg()
        .equals(other.getErrormsg())) return false;
    if (!getQuestsList()
        .equals(other.getQuestsList())) return false;
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
    if (getQuestsCount() > 0) {
      hash = (37 * hash) + QUESTS_FIELD_NUMBER;
      hash = (53 * hash) + getQuestsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.QuestListResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.QuestListResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.QuestListResponse parseFrom(
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
  public static Builder newBuilder(com.duke.protobuf.data.QuestListResponse prototype) {
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
   * Protobuf type {@code com.duke.protobuf.data.QuestListResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.protobuf.data.QuestListResponse)
      com.duke.protobuf.data.QuestListResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_QuestListResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_QuestListResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.protobuf.data.QuestListResponse.class, com.duke.protobuf.data.QuestListResponse.Builder.class);
    }

    // Construct using com.duke.protobuf.data.QuestListResponse.newBuilder()
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
        getQuestsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      result_ = 0;

      errormsg_ = "";

      if (questsBuilder_ == null) {
        quests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        questsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_QuestListResponse_descriptor;
    }

    @java.lang.Override
    public com.duke.protobuf.data.QuestListResponse getDefaultInstanceForType() {
      return com.duke.protobuf.data.QuestListResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.protobuf.data.QuestListResponse build() {
      com.duke.protobuf.data.QuestListResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.protobuf.data.QuestListResponse buildPartial() {
      com.duke.protobuf.data.QuestListResponse result = new com.duke.protobuf.data.QuestListResponse(this);
      int from_bitField0_ = bitField0_;
      result.result_ = result_;
      result.errormsg_ = errormsg_;
      if (questsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          quests_ = java.util.Collections.unmodifiableList(quests_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.quests_ = quests_;
      } else {
        result.quests_ = questsBuilder_.build();
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
      if (other instanceof com.duke.protobuf.data.QuestListResponse) {
        return mergeFrom((com.duke.protobuf.data.QuestListResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.protobuf.data.QuestListResponse other) {
      if (other == com.duke.protobuf.data.QuestListResponse.getDefaultInstance()) return this;
      if (other.result_ != 0) {
        setResultValue(other.getResultValue());
      }
      if (!other.getErrormsg().isEmpty()) {
        errormsg_ = other.errormsg_;
        onChanged();
      }
      if (questsBuilder_ == null) {
        if (!other.quests_.isEmpty()) {
          if (quests_.isEmpty()) {
            quests_ = other.quests_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureQuestsIsMutable();
            quests_.addAll(other.quests_);
          }
          onChanged();
        }
      } else {
        if (!other.quests_.isEmpty()) {
          if (questsBuilder_.isEmpty()) {
            questsBuilder_.dispose();
            questsBuilder_ = null;
            quests_ = other.quests_;
            bitField0_ = (bitField0_ & ~0x00000001);
            questsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQuestsFieldBuilder() : null;
          } else {
            questsBuilder_.addAllMessages(other.quests_);
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
      com.duke.protobuf.data.QuestListResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.protobuf.data.QuestListResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int result_ = 0;
    /**
     * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
     * @return The enum numeric value on the wire for result.
     */
    @java.lang.Override public int getResultValue() {
      return result_;
    }
    /**
     * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
     * @param value The enum numeric value on the wire for result to set.
     * @return This builder for chaining.
     */
    public Builder setResultValue(int value) {
      
      result_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
     * @return The result.
     */
    @java.lang.Override
    public com.duke.protobuf.data.RESULT getResult() {
      @SuppressWarnings("deprecation")
      com.duke.protobuf.data.RESULT result = com.duke.protobuf.data.RESULT.valueOf(result_);
      return result == null ? com.duke.protobuf.data.RESULT.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
     * @param value The result to set.
     * @return This builder for chaining.
     */
    public Builder setResult(com.duke.protobuf.data.RESULT value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      result_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
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

    private java.util.List<com.duke.protobuf.data.NQuestInfo> quests_ =
      java.util.Collections.emptyList();
    private void ensureQuestsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        quests_ = new java.util.ArrayList<com.duke.protobuf.data.NQuestInfo>(quests_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.duke.protobuf.data.NQuestInfo, com.duke.protobuf.data.NQuestInfo.Builder, com.duke.protobuf.data.NQuestInfoOrBuilder> questsBuilder_;

    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public java.util.List<com.duke.protobuf.data.NQuestInfo> getQuestsList() {
      if (questsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(quests_);
      } else {
        return questsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public int getQuestsCount() {
      if (questsBuilder_ == null) {
        return quests_.size();
      } else {
        return questsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public com.duke.protobuf.data.NQuestInfo getQuests(int index) {
      if (questsBuilder_ == null) {
        return quests_.get(index);
      } else {
        return questsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder setQuests(
        int index, com.duke.protobuf.data.NQuestInfo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.set(index, value);
        onChanged();
      } else {
        questsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder setQuests(
        int index, com.duke.protobuf.data.NQuestInfo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.set(index, builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder addQuests(com.duke.protobuf.data.NQuestInfo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.add(value);
        onChanged();
      } else {
        questsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder addQuests(
        int index, com.duke.protobuf.data.NQuestInfo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.add(index, value);
        onChanged();
      } else {
        questsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder addQuests(
        com.duke.protobuf.data.NQuestInfo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.add(builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder addQuests(
        int index, com.duke.protobuf.data.NQuestInfo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.add(index, builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder addAllQuests(
        java.lang.Iterable<? extends com.duke.protobuf.data.NQuestInfo> values) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, quests_);
        onChanged();
      } else {
        questsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder clearQuests() {
      if (questsBuilder_ == null) {
        quests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        questsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public Builder removeQuests(int index) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.remove(index);
        onChanged();
      } else {
        questsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public com.duke.protobuf.data.NQuestInfo.Builder getQuestsBuilder(
        int index) {
      return getQuestsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public com.duke.protobuf.data.NQuestInfoOrBuilder getQuestsOrBuilder(
        int index) {
      if (questsBuilder_ == null) {
        return quests_.get(index);  } else {
        return questsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public java.util.List<? extends com.duke.protobuf.data.NQuestInfoOrBuilder> 
         getQuestsOrBuilderList() {
      if (questsBuilder_ != null) {
        return questsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(quests_);
      }
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public com.duke.protobuf.data.NQuestInfo.Builder addQuestsBuilder() {
      return getQuestsFieldBuilder().addBuilder(
          com.duke.protobuf.data.NQuestInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public com.duke.protobuf.data.NQuestInfo.Builder addQuestsBuilder(
        int index) {
      return getQuestsFieldBuilder().addBuilder(
          index, com.duke.protobuf.data.NQuestInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .com.duke.protobuf.data.NQuestInfo quests = 3;</code>
     */
    public java.util.List<com.duke.protobuf.data.NQuestInfo.Builder> 
         getQuestsBuilderList() {
      return getQuestsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.duke.protobuf.data.NQuestInfo, com.duke.protobuf.data.NQuestInfo.Builder, com.duke.protobuf.data.NQuestInfoOrBuilder> 
        getQuestsFieldBuilder() {
      if (questsBuilder_ == null) {
        questsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.duke.protobuf.data.NQuestInfo, com.duke.protobuf.data.NQuestInfo.Builder, com.duke.protobuf.data.NQuestInfoOrBuilder>(
                quests_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        quests_ = null;
      }
      return questsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.duke.protobuf.data.QuestListResponse)
  }

  // @@protoc_insertion_point(class_scope:com.duke.protobuf.data.QuestListResponse)
  private static final com.duke.protobuf.data.QuestListResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.protobuf.data.QuestListResponse();
  }

  public static com.duke.protobuf.data.QuestListResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QuestListResponse>
      PARSER = new com.google.protobuf.AbstractParser<QuestListResponse>() {
    @java.lang.Override
    public QuestListResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QuestListResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QuestListResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QuestListResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.protobuf.data.QuestListResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
