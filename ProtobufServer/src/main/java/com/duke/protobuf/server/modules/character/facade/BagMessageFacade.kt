package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.BagSaveRequest
import com.duke.protobuf.data.BagSaveResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.BagService
import com.duke.protobuf.server.modules.character.service.CharacterService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class BagMessageFacade(
    val service: BagService,
    val characterService: CharacterService,
) {
    @MessageHandler(BagSaveRequest::class)
    fun onSaveBag(req: BagSaveRequest, sessionChar: PlayerCharacter): BagSaveResponse {
        logger.info("BagSaveRequest: :角色{}:背包格子数{}", sessionChar.id, req.bagInfo?.unlocked)

        val tBag = service.getByCharacterId(sessionChar.id)
        if (req.bagInfo != null) {
            tBag.items = req.bagInfo.items.toByteArray()
            service.save(tBag)
        }

        return BagSaveResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .build()
    }

    companion object { private val logger = LoggerFactory.getLogger(BagMessageFacade::class.java) }
}
