// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/message.proto

package com.duke.proto.data;

public interface NetMessageResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.duke.proto.data.NetMessageResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.duke.proto.data.UserRegisterResponse userRegister = 1;</code>
   * @return Whether the userRegister field is set.
   */
  boolean hasUserRegister();
  /**
   * <code>.com.duke.proto.data.UserRegisterResponse userRegister = 1;</code>
   * @return The userRegister.
   */
  com.duke.proto.data.UserRegisterResponse getUserRegister();
  /**
   * <code>.com.duke.proto.data.UserRegisterResponse userRegister = 1;</code>
   */
  com.duke.proto.data.UserRegisterResponseOrBuilder getUserRegisterOrBuilder();

  /**
   * <code>.com.duke.proto.data.UserLoginResponse userLogin = 2;</code>
   * @return Whether the userLogin field is set.
   */
  boolean hasUserLogin();
  /**
   * <code>.com.duke.proto.data.UserLoginResponse userLogin = 2;</code>
   * @return The userLogin.
   */
  com.duke.proto.data.UserLoginResponse getUserLogin();
  /**
   * <code>.com.duke.proto.data.UserLoginResponse userLogin = 2;</code>
   */
  com.duke.proto.data.UserLoginResponseOrBuilder getUserLoginOrBuilder();

  /**
   * <code>.com.duke.proto.data.UserCreateCharacterResponse createChar = 3;</code>
   * @return Whether the createChar field is set.
   */
  boolean hasCreateChar();
  /**
   * <code>.com.duke.proto.data.UserCreateCharacterResponse createChar = 3;</code>
   * @return The createChar.
   */
  com.duke.proto.data.UserCreateCharacterResponse getCreateChar();
  /**
   * <code>.com.duke.proto.data.UserCreateCharacterResponse createChar = 3;</code>
   */
  com.duke.proto.data.UserCreateCharacterResponseOrBuilder getCreateCharOrBuilder();

  /**
   * <code>.com.duke.proto.data.UserGameEnterResponse gameEnter = 4;</code>
   * @return Whether the gameEnter field is set.
   */
  boolean hasGameEnter();
  /**
   * <code>.com.duke.proto.data.UserGameEnterResponse gameEnter = 4;</code>
   * @return The gameEnter.
   */
  com.duke.proto.data.UserGameEnterResponse getGameEnter();
  /**
   * <code>.com.duke.proto.data.UserGameEnterResponse gameEnter = 4;</code>
   */
  com.duke.proto.data.UserGameEnterResponseOrBuilder getGameEnterOrBuilder();

  /**
   * <code>.com.duke.proto.data.UserGameLeaveResponse gameLeave = 5;</code>
   * @return Whether the gameLeave field is set.
   */
  boolean hasGameLeave();
  /**
   * <code>.com.duke.proto.data.UserGameLeaveResponse gameLeave = 5;</code>
   * @return The gameLeave.
   */
  com.duke.proto.data.UserGameLeaveResponse getGameLeave();
  /**
   * <code>.com.duke.proto.data.UserGameLeaveResponse gameLeave = 5;</code>
   */
  com.duke.proto.data.UserGameLeaveResponseOrBuilder getGameLeaveOrBuilder();

  /**
   * <code>.com.duke.proto.data.MapCharacterEnterResponse mapCharacterEnter = 6;</code>
   * @return Whether the mapCharacterEnter field is set.
   */
  boolean hasMapCharacterEnter();
  /**
   * <code>.com.duke.proto.data.MapCharacterEnterResponse mapCharacterEnter = 6;</code>
   * @return The mapCharacterEnter.
   */
  com.duke.proto.data.MapCharacterEnterResponse getMapCharacterEnter();
  /**
   * <code>.com.duke.proto.data.MapCharacterEnterResponse mapCharacterEnter = 6;</code>
   */
  com.duke.proto.data.MapCharacterEnterResponseOrBuilder getMapCharacterEnterOrBuilder();

  /**
   * <code>.com.duke.proto.data.MapCharacterLeaveResponse mapCharacterLeave = 7;</code>
   * @return Whether the mapCharacterLeave field is set.
   */
  boolean hasMapCharacterLeave();
  /**
   * <code>.com.duke.proto.data.MapCharacterLeaveResponse mapCharacterLeave = 7;</code>
   * @return The mapCharacterLeave.
   */
  com.duke.proto.data.MapCharacterLeaveResponse getMapCharacterLeave();
  /**
   * <code>.com.duke.proto.data.MapCharacterLeaveResponse mapCharacterLeave = 7;</code>
   */
  com.duke.proto.data.MapCharacterLeaveResponseOrBuilder getMapCharacterLeaveOrBuilder();

  /**
   * <code>.com.duke.proto.data.MapEntitySyncResponse mapEntitySync = 8;</code>
   * @return Whether the mapEntitySync field is set.
   */
  boolean hasMapEntitySync();
  /**
   * <code>.com.duke.proto.data.MapEntitySyncResponse mapEntitySync = 8;</code>
   * @return The mapEntitySync.
   */
  com.duke.proto.data.MapEntitySyncResponse getMapEntitySync();
  /**
   * <code>.com.duke.proto.data.MapEntitySyncResponse mapEntitySync = 8;</code>
   */
  com.duke.proto.data.MapEntitySyncResponseOrBuilder getMapEntitySyncOrBuilder();
}
