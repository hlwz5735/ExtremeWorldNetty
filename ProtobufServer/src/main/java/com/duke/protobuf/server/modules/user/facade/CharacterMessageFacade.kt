package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.data.RESULT
import com.duke.protobuf.data.UserCreateCharacterRequest
import com.duke.protobuf.data.UserCreateCharacterResponse
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.user.service.CharacterService
import com.duke.protobuf.server.net.pojo.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class CharacterMessageFacade(
    private val service: CharacterService
) {
    @MessageHandler(UserCreateCharacterRequest::class)
    fun onCreateCharacter(request: UserCreateCharacterRequest, channel: Channel): UserCreateCharacterResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return UserCreateCharacterResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在！")
                .build()

        logger.info("角色创建请求 - 用户ID：${session.user.id} 名称：${request.name} 职业：${request.class_}")
        return try {
            service.createCharacter(session, request.name, request.class_)

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

    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}
