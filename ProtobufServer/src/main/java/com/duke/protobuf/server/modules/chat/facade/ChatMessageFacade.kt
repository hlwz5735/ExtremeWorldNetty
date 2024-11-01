package com.duke.protobuf.server.modules.chat.facade

import com.duke.protobuf.data.ProtoMessages
import com.duke.protobuf.data.ProtoMessages.ChatRequest
import com.duke.protobuf.data.ProtoMessages.ChatResponse
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.chat.service.ChatService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class ChatMessageFacade(
    val service: ChatService,
    val onlineCharacterManager: OnlineCharacterManager,
) {
    @MessageHandler(ChatRequest::class)
    fun onChat(req: ChatRequest, playerCharacter: PlayerCharacter): ChatResponse? {
        if (req.message.fromId != playerCharacter.dbId) {
            logger.warn("发现有人伪造消息发送请求！真实角色ID:{}, 伪造角色ID:{}", playerCharacter.dbId, req.message.fromId)
            return null
        }
        if (req.message.channel == ProtoMessages.NChatMessage.CHAT_CHANNEL.PRIVATE) {
            val targetChar = onlineCharacterManager[req.message.toId]
            if (targetChar == null) {
                return ChatResponse.newBuilder()
                    .setResult(ProtoMessages.RESULT.FAILED)
                    .setErrormsg("私聊对象不在线！")
                    .addPrivateChatMessages(req.message)
                    .build()
            } else {
                service.sendPrivateChat(req.message, playerCharacter, targetChar.character!!)
                return ChatResponse.newBuilder()
                    .setResult(ProtoMessages.RESULT.SUCCESS)
                    .addPrivateChatMessages(req.message)
                    .build()
            }
        } else {
            service.addMessage(playerCharacter, req.message)
            return ChatResponse.newBuilder()
                .setResult(ProtoMessages.RESULT.SUCCESS)
                .build()
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ChatMessageFacade::class.java)
    }
}
