// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.duke.protobuf.data;

public interface QuestAcceptResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.protobuf.data.QuestAcceptResponse)
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
   * <code>.com.duke.protobuf.data.NQuestInfo quest = 3;</code>
   * @return Whether the quest field is set.
   */
  boolean hasQuest();
  /**
   * <code>.com.duke.protobuf.data.NQuestInfo quest = 3;</code>
   * @return The quest.
   */
  com.duke.protobuf.data.NQuestInfo getQuest();
  /**
   * <code>.com.duke.protobuf.data.NQuestInfo quest = 3;</code>
   */
  com.duke.protobuf.data.NQuestInfoOrBuilder getQuestOrBuilder();
}
