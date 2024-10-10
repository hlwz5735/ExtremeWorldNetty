package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.ItemEquipRequest
import com.duke.protobuf.data.ItemEquipResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.CharacterService
import com.duke.protobuf.server.modules.character.service.EquipService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class CharacterMessageFacade(
    private val service: CharacterService,
    private val equipService: EquipService,
) {
    @MessageHandler(ItemEquipRequest::class)
    fun onEquipRequest(req: ItemEquipRequest, sessionChar: PlayerCharacter): ItemEquipResponse {
        logger.info("收到装备穿脱协议请求。")
        val result = equipService.equipItem(sessionChar, req.slot, req.itemId, req.isPutOn)
        return ItemEquipResponse.newBuilder()
            .setResult(if (result) RESULT.SUCCESS else RESULT.FAILED)
            .build()
    }

    companion object { private val logger = LoggerFactory.getLogger(CharacterMessageFacade::class.java) }
}
