package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.user.service.UserService
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class UserMessageFacade(private val service: UserService) {
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

    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}
