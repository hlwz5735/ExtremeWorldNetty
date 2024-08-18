// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

/**
 * Protobuf enum {@code com.duke.protobuf.data.ITEM_TYPE}
 */
public enum ITEM_TYPE
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * 常规道具
   * </pre>
   *
   * <code>NORMAL = 0;</code>
   */
  NORMAL(0),
  /**
   * <pre>
   * 材料道具
   * </pre>
   *
   * <code>MATERIAL = 1;</code>
   */
  MATERIAL(1),
  /**
   * <pre>
   * 任务道具
   * </pre>
   *
   * <code>TASK = 2;</code>
   */
  TASK(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * 常规道具
   * </pre>
   *
   * <code>NORMAL = 0;</code>
   */
  public static final int NORMAL_VALUE = 0;
  /**
   * <pre>
   * 材料道具
   * </pre>
   *
   * <code>MATERIAL = 1;</code>
   */
  public static final int MATERIAL_VALUE = 1;
  /**
   * <pre>
   * 任务道具
   * </pre>
   *
   * <code>TASK = 2;</code>
   */
  public static final int TASK_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static ITEM_TYPE valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ITEM_TYPE forNumber(int value) {
    switch (value) {
      case 0: return NORMAL;
      case 1: return MATERIAL;
      case 2: return TASK;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ITEM_TYPE>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ITEM_TYPE> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ITEM_TYPE>() {
          public ITEM_TYPE findValueByNumber(int number) {
            return ITEM_TYPE.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.duke.protobuf.data.Message.getDescriptor().getEnumTypes().get(4);
  }

  private static final ITEM_TYPE[] VALUES = values();

  public static ITEM_TYPE valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private ITEM_TYPE(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:com.duke.protobuf.data.ITEM_TYPE)
}
