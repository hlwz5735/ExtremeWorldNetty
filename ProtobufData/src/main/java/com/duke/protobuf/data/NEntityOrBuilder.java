// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

public interface NEntityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.protobuf.data.NEntity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>.com.duke.protobuf.data.NVector3 position = 2;</code>
   * @return Whether the position field is set.
   */
  boolean hasPosition();
  /**
   * <code>.com.duke.protobuf.data.NVector3 position = 2;</code>
   * @return The position.
   */
  com.duke.protobuf.data.NVector3 getPosition();
  /**
   * <code>.com.duke.protobuf.data.NVector3 position = 2;</code>
   */
  com.duke.protobuf.data.NVector3OrBuilder getPositionOrBuilder();

  /**
   * <code>.com.duke.protobuf.data.NVector3 direction = 3;</code>
   * @return Whether the direction field is set.
   */
  boolean hasDirection();
  /**
   * <code>.com.duke.protobuf.data.NVector3 direction = 3;</code>
   * @return The direction.
   */
  com.duke.protobuf.data.NVector3 getDirection();
  /**
   * <code>.com.duke.protobuf.data.NVector3 direction = 3;</code>
   */
  com.duke.protobuf.data.NVector3OrBuilder getDirectionOrBuilder();

  /**
   * <code>int32 speed = 4;</code>
   * @return The speed.
   */
  int getSpeed();
}
