package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.QuestService
import com.duke.protobuf.server.modules.game.net.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class QuestMessageFacade(
    private val service: QuestService,
) {
    @MessageHandler(QuestAcceptRequest::class)
    fun onQuestAccept(req: QuestAcceptRequest, channel: Channel): QuestAcceptResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return QuestAcceptResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return QuestAcceptResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        logger.info("收到接收任务请求。 角色ID: {}, 任务ID: {}", sessionChar.dbId, req.questId)

        val result = sessionChar.questManager.acceptQuest(req.questId)
        if (result.first == true) {
            return QuestAcceptResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setQuest(result.second as NQuestInfo)
                .build()
        } else {
            return QuestAcceptResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg(result.second as String?)
                .build()
        }
    }

    @MessageHandler(QuestSubmitRequest::class)
    fun onQuestSubmit(req: QuestSubmitRequest, channel: Channel): QuestSubmitResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return QuestSubmitResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return QuestSubmitResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        logger.info("收到提交任务请求。 角色ID: {}, 任务ID: {}", sessionChar.dbId, req.questId)

        val result = sessionChar.questManager.submitQuest(req.questId)
        if (result.first == true) {
            return QuestSubmitResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setQuest(result.second as NQuestInfo)
                .build()
        } else {
            return QuestSubmitResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg(result.second as String?)
                .build()
        }
    }

    companion object { private val logger = LoggerFactory.getLogger(QuestMessageFacade::class.java) }
}
