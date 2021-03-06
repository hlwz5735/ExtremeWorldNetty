// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.protobuf.data;

/**
 * Protobuf type {@code com.duke.protobuf.data.NCharacterInfo}
 */
public final class NCharacterInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.duke.protobuf.data.NCharacterInfo)
    NCharacterInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NCharacterInfo.newBuilder() to construct.
  private NCharacterInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NCharacterInfo() {
    name_ = "";
    type_ = 0;
    class__ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new NCharacterInfo();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NCharacterInfo(
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

            id_ = input.readInt32();
            break;
          }
          case 16: {

            tid_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 32: {
            int rawValue = input.readEnum();

            type_ = rawValue;
            break;
          }
          case 40: {
            int rawValue = input.readEnum();

            class__ = rawValue;
            break;
          }
          case 48: {

            level_ = input.readInt32();
            break;
          }
          case 56: {

            mapId_ = input.readInt32();
            break;
          }
          case 66: {
            com.duke.protobuf.data.NEntity.Builder subBuilder = null;
            if (entity_ != null) {
              subBuilder = entity_.toBuilder();
            }
            entity_ = input.readMessage(com.duke.protobuf.data.NEntity.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(entity_);
              entity_ = subBuilder.buildPartial();
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
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NCharacterInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NCharacterInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.duke.protobuf.data.NCharacterInfo.class, com.duke.protobuf.data.NCharacterInfo.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public int getId() {
    return id_;
  }

  public static final int TID_FIELD_NUMBER = 2;
  private int tid_;
  /**
   * <code>int32 tid = 2;</code>
   * @return The tid.
   */
  @java.lang.Override
  public int getTid() {
    return tid_;
  }

  public static final int NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 3;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TYPE_FIELD_NUMBER = 4;
  private int type_;
  /**
   * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
   * @return The enum numeric value on the wire for type.
   */
  @java.lang.Override public int getTypeValue() {
    return type_;
  }
  /**
   * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
   * @return The type.
   */
  @java.lang.Override public com.duke.protobuf.data.CHARACTER_TYPE getType() {
    @SuppressWarnings("deprecation")
    com.duke.protobuf.data.CHARACTER_TYPE result = com.duke.protobuf.data.CHARACTER_TYPE.valueOf(type_);
    return result == null ? com.duke.protobuf.data.CHARACTER_TYPE.UNRECOGNIZED : result;
  }

  public static final int CLASS_FIELD_NUMBER = 5;
  private int class__;
  /**
   * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
   * @return The enum numeric value on the wire for class.
   */
  @java.lang.Override public int getClass_Value() {
    return class__;
  }
  /**
   * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
   * @return The class.
   */
  @java.lang.Override public com.duke.protobuf.data.CHARACTER_CLASS getClass_() {
    @SuppressWarnings("deprecation")
    com.duke.protobuf.data.CHARACTER_CLASS result = com.duke.protobuf.data.CHARACTER_CLASS.valueOf(class__);
    return result == null ? com.duke.protobuf.data.CHARACTER_CLASS.UNRECOGNIZED : result;
  }

  public static final int LEVEL_FIELD_NUMBER = 6;
  private int level_;
  /**
   * <code>int32 level = 6;</code>
   * @return The level.
   */
  @java.lang.Override
  public int getLevel() {
    return level_;
  }

  public static final int MAPID_FIELD_NUMBER = 7;
  private int mapId_;
  /**
   * <code>int32 mapId = 7;</code>
   * @return The mapId.
   */
  @java.lang.Override
  public int getMapId() {
    return mapId_;
  }

  public static final int ENTITY_FIELD_NUMBER = 8;
  private com.duke.protobuf.data.NEntity entity_;
  /**
   * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
   * @return Whether the entity field is set.
   */
  @java.lang.Override
  public boolean hasEntity() {
    return entity_ != null;
  }
  /**
   * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
   * @return The entity.
   */
  @java.lang.Override
  public com.duke.protobuf.data.NEntity getEntity() {
    return entity_ == null ? com.duke.protobuf.data.NEntity.getDefaultInstance() : entity_;
  }
  /**
   * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
   */
  @java.lang.Override
  public com.duke.protobuf.data.NEntityOrBuilder getEntityOrBuilder() {
    return getEntity();
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
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (tid_ != 0) {
      output.writeInt32(2, tid_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
    }
    if (type_ != com.duke.protobuf.data.CHARACTER_TYPE.Player.getNumber()) {
      output.writeEnum(4, type_);
    }
    if (class__ != com.duke.protobuf.data.CHARACTER_CLASS.NONE.getNumber()) {
      output.writeEnum(5, class__);
    }
    if (level_ != 0) {
      output.writeInt32(6, level_);
    }
    if (mapId_ != 0) {
      output.writeInt32(7, mapId_);
    }
    if (entity_ != null) {
      output.writeMessage(8, getEntity());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (tid_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, tid_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
    }
    if (type_ != com.duke.protobuf.data.CHARACTER_TYPE.Player.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, type_);
    }
    if (class__ != com.duke.protobuf.data.CHARACTER_CLASS.NONE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(5, class__);
    }
    if (level_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, level_);
    }
    if (mapId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, mapId_);
    }
    if (entity_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(8, getEntity());
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
    if (!(obj instanceof com.duke.protobuf.data.NCharacterInfo)) {
      return super.equals(obj);
    }
    com.duke.protobuf.data.NCharacterInfo other = (com.duke.protobuf.data.NCharacterInfo) obj;

    if (getId()
        != other.getId()) return false;
    if (getTid()
        != other.getTid()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (type_ != other.type_) return false;
    if (class__ != other.class__) return false;
    if (getLevel()
        != other.getLevel()) return false;
    if (getMapId()
        != other.getMapId()) return false;
    if (hasEntity() != other.hasEntity()) return false;
    if (hasEntity()) {
      if (!getEntity()
          .equals(other.getEntity())) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + TID_FIELD_NUMBER;
    hash = (53 * hash) + getTid();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (37 * hash) + CLASS_FIELD_NUMBER;
    hash = (53 * hash) + class__;
    hash = (37 * hash) + LEVEL_FIELD_NUMBER;
    hash = (53 * hash) + getLevel();
    hash = (37 * hash) + MAPID_FIELD_NUMBER;
    hash = (53 * hash) + getMapId();
    if (hasEntity()) {
      hash = (37 * hash) + ENTITY_FIELD_NUMBER;
      hash = (53 * hash) + getEntity().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.duke.protobuf.data.NCharacterInfo parseFrom(
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
  public static Builder newBuilder(com.duke.protobuf.data.NCharacterInfo prototype) {
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
   * Protobuf type {@code com.duke.protobuf.data.NCharacterInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.duke.protobuf.data.NCharacterInfo)
      com.duke.protobuf.data.NCharacterInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NCharacterInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NCharacterInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.duke.protobuf.data.NCharacterInfo.class, com.duke.protobuf.data.NCharacterInfo.Builder.class);
    }

    // Construct using com.duke.protobuf.data.NCharacterInfo.newBuilder()
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
      id_ = 0;

      tid_ = 0;

      name_ = "";

      type_ = 0;

      class__ = 0;

      level_ = 0;

      mapId_ = 0;

      if (entityBuilder_ == null) {
        entity_ = null;
      } else {
        entity_ = null;
        entityBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.duke.protobuf.data.Message.internal_static_com_duke_protobuf_data_NCharacterInfo_descriptor;
    }

    @java.lang.Override
    public com.duke.protobuf.data.NCharacterInfo getDefaultInstanceForType() {
      return com.duke.protobuf.data.NCharacterInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.duke.protobuf.data.NCharacterInfo build() {
      com.duke.protobuf.data.NCharacterInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.duke.protobuf.data.NCharacterInfo buildPartial() {
      com.duke.protobuf.data.NCharacterInfo result = new com.duke.protobuf.data.NCharacterInfo(this);
      result.id_ = id_;
      result.tid_ = tid_;
      result.name_ = name_;
      result.type_ = type_;
      result.class__ = class__;
      result.level_ = level_;
      result.mapId_ = mapId_;
      if (entityBuilder_ == null) {
        result.entity_ = entity_;
      } else {
        result.entity_ = entityBuilder_.build();
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
      if (other instanceof com.duke.protobuf.data.NCharacterInfo) {
        return mergeFrom((com.duke.protobuf.data.NCharacterInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.duke.protobuf.data.NCharacterInfo other) {
      if (other == com.duke.protobuf.data.NCharacterInfo.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getTid() != 0) {
        setTid(other.getTid());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
      if (other.class__ != 0) {
        setClass_Value(other.getClass_Value());
      }
      if (other.getLevel() != 0) {
        setLevel(other.getLevel());
      }
      if (other.getMapId() != 0) {
        setMapId(other.getMapId());
      }
      if (other.hasEntity()) {
        mergeEntity(other.getEntity());
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
      com.duke.protobuf.data.NCharacterInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.duke.protobuf.data.NCharacterInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private int tid_ ;
    /**
     * <code>int32 tid = 2;</code>
     * @return The tid.
     */
    @java.lang.Override
    public int getTid() {
      return tid_;
    }
    /**
     * <code>int32 tid = 2;</code>
     * @param value The tid to set.
     * @return This builder for chaining.
     */
    public Builder setTid(int value) {
      
      tid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 tid = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTid() {
      
      tid_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 3;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private int type_ = 0;
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override public int getTypeValue() {
      return type_;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
     * @param value The enum numeric value on the wire for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeValue(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
     * @return The type.
     */
    @java.lang.Override
    public com.duke.protobuf.data.CHARACTER_TYPE getType() {
      @SuppressWarnings("deprecation")
      com.duke.protobuf.data.CHARACTER_TYPE result = com.duke.protobuf.data.CHARACTER_TYPE.valueOf(type_);
      return result == null ? com.duke.protobuf.data.CHARACTER_TYPE.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(com.duke.protobuf.data.CHARACTER_TYPE value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_TYPE type = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private int class__ = 0;
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
     * @return The enum numeric value on the wire for class.
     */
    @java.lang.Override public int getClass_Value() {
      return class__;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
     * @param value The enum numeric value on the wire for class to set.
     * @return This builder for chaining.
     */
    public Builder setClass_Value(int value) {
      
      class__ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
     * @return The class.
     */
    @java.lang.Override
    public com.duke.protobuf.data.CHARACTER_CLASS getClass_() {
      @SuppressWarnings("deprecation")
      com.duke.protobuf.data.CHARACTER_CLASS result = com.duke.protobuf.data.CHARACTER_CLASS.valueOf(class__);
      return result == null ? com.duke.protobuf.data.CHARACTER_CLASS.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
     * @param value The class to set.
     * @return This builder for chaining.
     */
    public Builder setClass_(com.duke.protobuf.data.CHARACTER_CLASS value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      class__ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.CHARACTER_CLASS class = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearClass_() {
      
      class__ = 0;
      onChanged();
      return this;
    }

    private int level_ ;
    /**
     * <code>int32 level = 6;</code>
     * @return The level.
     */
    @java.lang.Override
    public int getLevel() {
      return level_;
    }
    /**
     * <code>int32 level = 6;</code>
     * @param value The level to set.
     * @return This builder for chaining.
     */
    public Builder setLevel(int value) {
      
      level_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 level = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearLevel() {
      
      level_ = 0;
      onChanged();
      return this;
    }

    private int mapId_ ;
    /**
     * <code>int32 mapId = 7;</code>
     * @return The mapId.
     */
    @java.lang.Override
    public int getMapId() {
      return mapId_;
    }
    /**
     * <code>int32 mapId = 7;</code>
     * @param value The mapId to set.
     * @return This builder for chaining.
     */
    public Builder setMapId(int value) {
      
      mapId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 mapId = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearMapId() {
      
      mapId_ = 0;
      onChanged();
      return this;
    }

    private com.duke.protobuf.data.NEntity entity_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NEntity, com.duke.protobuf.data.NEntity.Builder, com.duke.protobuf.data.NEntityOrBuilder> entityBuilder_;
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     * @return Whether the entity field is set.
     */
    public boolean hasEntity() {
      return entityBuilder_ != null || entity_ != null;
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     * @return The entity.
     */
    public com.duke.protobuf.data.NEntity getEntity() {
      if (entityBuilder_ == null) {
        return entity_ == null ? com.duke.protobuf.data.NEntity.getDefaultInstance() : entity_;
      } else {
        return entityBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public Builder setEntity(com.duke.protobuf.data.NEntity value) {
      if (entityBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        entity_ = value;
        onChanged();
      } else {
        entityBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public Builder setEntity(
        com.duke.protobuf.data.NEntity.Builder builderForValue) {
      if (entityBuilder_ == null) {
        entity_ = builderForValue.build();
        onChanged();
      } else {
        entityBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public Builder mergeEntity(com.duke.protobuf.data.NEntity value) {
      if (entityBuilder_ == null) {
        if (entity_ != null) {
          entity_ =
            com.duke.protobuf.data.NEntity.newBuilder(entity_).mergeFrom(value).buildPartial();
        } else {
          entity_ = value;
        }
        onChanged();
      } else {
        entityBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public Builder clearEntity() {
      if (entityBuilder_ == null) {
        entity_ = null;
        onChanged();
      } else {
        entity_ = null;
        entityBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public com.duke.protobuf.data.NEntity.Builder getEntityBuilder() {
      
      onChanged();
      return getEntityFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    public com.duke.protobuf.data.NEntityOrBuilder getEntityOrBuilder() {
      if (entityBuilder_ != null) {
        return entityBuilder_.getMessageOrBuilder();
      } else {
        return entity_ == null ?
            com.duke.protobuf.data.NEntity.getDefaultInstance() : entity_;
      }
    }
    /**
     * <code>.com.duke.protobuf.data.NEntity entity = 8;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.duke.protobuf.data.NEntity, com.duke.protobuf.data.NEntity.Builder, com.duke.protobuf.data.NEntityOrBuilder> 
        getEntityFieldBuilder() {
      if (entityBuilder_ == null) {
        entityBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.duke.protobuf.data.NEntity, com.duke.protobuf.data.NEntity.Builder, com.duke.protobuf.data.NEntityOrBuilder>(
                getEntity(),
                getParentForChildren(),
                isClean());
        entity_ = null;
      }
      return entityBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.duke.protobuf.data.NCharacterInfo)
  }

  // @@protoc_insertion_point(class_scope:com.duke.protobuf.data.NCharacterInfo)
  private static final com.duke.protobuf.data.NCharacterInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.duke.protobuf.data.NCharacterInfo();
  }

  public static com.duke.protobuf.data.NCharacterInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NCharacterInfo>
      PARSER = new com.google.protobuf.AbstractParser<NCharacterInfo>() {
    @java.lang.Override
    public NCharacterInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NCharacterInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NCharacterInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NCharacterInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.duke.protobuf.data.NCharacterInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

