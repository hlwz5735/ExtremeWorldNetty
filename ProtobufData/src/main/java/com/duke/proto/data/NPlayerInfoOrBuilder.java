// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.proto.data;

public interface NPlayerInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.proto.data.NPlayerInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>repeated .com.duke.proto.data.NCharacterInfo characters = 2;</code>
   */
  java.util.List<com.duke.proto.data.NCharacterInfo> 
      getCharactersList();
  /**
   * <code>repeated .com.duke.proto.data.NCharacterInfo characters = 2;</code>
   */
  com.duke.proto.data.NCharacterInfo getCharacters(int index);
  /**
   * <code>repeated .com.duke.proto.data.NCharacterInfo characters = 2;</code>
   */
  int getCharactersCount();
  /**
   * <code>repeated .com.duke.proto.data.NCharacterInfo characters = 2;</code>
   */
  java.util.List<? extends com.duke.proto.data.NCharacterInfoOrBuilder> 
      getCharactersOrBuilderList();
  /**
   * <code>repeated .com.duke.proto.data.NCharacterInfo characters = 2;</code>
   */
  com.duke.proto.data.NCharacterInfoOrBuilder getCharactersOrBuilder(
      int index);
}
