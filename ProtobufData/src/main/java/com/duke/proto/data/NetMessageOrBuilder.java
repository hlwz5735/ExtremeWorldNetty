// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.proto.data;

public interface NetMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.proto.data.NetMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.duke.proto.data.NetMessageRequest Request = 1;</code>
   * @return Whether the request field is set.
   */
  boolean hasRequest();
  /**
   * <code>.com.duke.proto.data.NetMessageRequest Request = 1;</code>
   * @return The request.
   */
  com.duke.proto.data.NetMessageRequest getRequest();
  /**
   * <code>.com.duke.proto.data.NetMessageRequest Request = 1;</code>
   */
  com.duke.proto.data.NetMessageRequestOrBuilder getRequestOrBuilder();

  /**
   * <code>.com.duke.proto.data.NetMessageResponse Response = 2;</code>
   * @return Whether the response field is set.
   */
  boolean hasResponse();
  /**
   * <code>.com.duke.proto.data.NetMessageResponse Response = 2;</code>
   * @return The response.
   */
  com.duke.proto.data.NetMessageResponse getResponse();
  /**
   * <code>.com.duke.proto.data.NetMessageResponse Response = 2;</code>
   */
  com.duke.proto.data.NetMessageResponseOrBuilder getResponseOrBuilder();
}
