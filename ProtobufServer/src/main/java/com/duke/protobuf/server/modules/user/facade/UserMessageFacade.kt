package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.map.service.MapService
import com.duke.protobuf.server.modules.user.service.CharacterService
import com.duke.protobuf.server.modules.user.service.UserService
import com.duke.protobuf.server.net.OnlineUserManager
import com.duke.protobuf.server.net.pojo.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class UserMessageFacade(
    private val service: UserService,
    private val characterService: CharacterService,
    private val onlineUserManager: OnlineUserManager,
    private val mapService: MapService
) {
    @MessageHandler(UserLoginRequest::class)
    fun onUserLogin(request: UserLoginRequest, channel: Channel): UserLoginResponse {
        logger.info("用户登录请求: 用户名：${request.user}，密码：${request.passward}")

        val result = this.service.onLogin(request.user, request.passward, channel)
        val user = result.first

        return if (user != null) {
            val builder = UserLoginResponse.newBuilder()
                .setErrormsg("None")
                .setResult(RESULT.SUCCESS)
            builder.userinfoBuilder
                .setId(user.id!!)
                .setPlayer(builder.userinfoBuilder.playerBuilder
                    .setId(user.player!!.id!!)
                    .addAllCharacters(user.player!!.characters.map {
                        NCharacterInfo.newBuilder()
                            .setId(it.id!!)
                            .setName(it.name)
                            .setClass_(it.clazz)
                            .build()
                    })
                    .build())
            builder.build()
        } else {
            UserLoginResponse.newBuilder()
                .setErrormsg(result.second)
                .setResult(RESULT.FAILED)
                .build()
        }
    }

    @MessageHandler(UserRegisterRequest::class)
    fun onRegister(request: UserRegisterRequest): UserRegisterResponse {
        logger.info("用户注册请求: 用户名：${request.user}，密码：${request.passward}")
        val result = this.service.registerUser(request.user, request.passward)
        return if (result.first == true) {
            UserRegisterResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setErrormsg("NONE")
                .build()
        } else {
            UserRegisterResponse.newBuilder()
                .setErrormsg(result.second)
                .setResult(RESULT.FAILED)
                .build()
        }
    }

    @MessageHandler(UserCreateCharacterRequest::class)
    fun onCreateCharacter(request: UserCreateCharacterRequest, channel: Channel): UserCreateCharacterResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return UserCreateCharacterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在！")
                .build()

        logger.info("角色创建请求 - 用户ID：${session.user.id} 名称：${request.name} 职业：${request.class_}")
        return try {
            characterService.createCharacter(session, request.name, request.class_)

            UserCreateCharacterResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setErrormsg("NONE")
                .build()
        } catch (e: Exception) {
            logger.error("创建角色时发生异常！", e)

            UserCreateCharacterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg(e.message)
                .build()
        }
    }

    /**
     * 用户正式进入游戏的请求
     */
    @MessageHandler(UserGameEnterRequest::class)
    fun onEnterGame(request: UserGameEnterRequest, channel: Channel): UserGameEnterResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel) ?:
            return UserGameEnterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()

        val characterCount = session.user.tableData.player?.characters?.size
        if (characterCount == null || characterCount <= request.characterIdx) {
            return UserGameEnterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        }
        val currentCharacter = session.user.tableData.player?.characters?.get(request.characterIdx)
            ?: return UserGameEnterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()

        // 将角色设置成玩家角色并放入在线角色列表
        val playerCharacter = PlayerCharacter(CHARACTER_TYPE.Player, currentCharacter)
        session.user.character = playerCharacter
        onlineUserManager.add(session.user)

        // 将玩家角色设置进入所在地图
        mapService.newCharacterEnter(playerCharacter.mapId, session)

        return UserGameEnterResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .setErrormsg("NONE")
            .build()
    }

    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}
