// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

public interface FriendListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.protobuf.data.FriendListResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
   * @return The enum numeric value on the wire for result.
   */
  int getResultValue();
  /**
   * <code>.com.duke.protobuf.data.RESULT result = 1;</code>
   * @return The result.
   */
  com.duke.protobuf.data.RESULT getResult();

  /**
   * <code>string errormsg = 2;</code>
   * @return The errormsg.
   */
  java.lang.String getErrormsg();
  /**
   * <code>string errormsg = 2;</code>
   * @return The bytes for errormsg.
   */
  com.google.protobuf.ByteString
      getErrormsgBytes();

  /**
   * <code>repeated .com.duke.protobuf.data.NFriendInfo friends = 3;</code>
   */
  java.util.List<com.duke.protobuf.data.NFriendInfo> 
      getFriendsList();
  /**
   * <code>repeated .com.duke.protobuf.data.NFriendInfo friends = 3;</code>
   */
  com.duke.protobuf.data.NFriendInfo getFriends(int index);
  /**
   * <code>repeated .com.duke.protobuf.data.NFriendInfo friends = 3;</code>
   */
  int getFriendsCount();
  /**
   * <code>repeated .com.duke.protobuf.data.NFriendInfo friends = 3;</code>
   */
  java.util.List<? extends com.duke.protobuf.data.NFriendInfoOrBuilder> 
      getFriendsOrBuilderList();
  /**
   * <code>repeated .com.duke.protobuf.data.NFriendInfo friends = 3;</code>
   */
  com.duke.protobuf.data.NFriendInfoOrBuilder getFriendsOrBuilder(
      int index);
}
