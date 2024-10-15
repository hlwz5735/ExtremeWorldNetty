package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.QuestService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class QuestMessageFacade(
    private val service: QuestService,
) {
    @MessageHandler(QuestAcceptRequest::class)
    fun onQuestAccept(req: QuestAcceptRequest, sessionChar: PlayerCharacter): QuestAcceptResponse {
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
    fun onQuestSubmit(req: QuestSubmitRequest, sessionChar: PlayerCharacter): QuestSubmitResponse {
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
