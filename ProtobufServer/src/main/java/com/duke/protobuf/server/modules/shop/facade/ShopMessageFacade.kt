package com.duke.protobuf.server.modules.shop.facade

import com.duke.protobuf.data.ItemPurchaseRequest
import com.duke.protobuf.data.ItemPurchaseResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.ItemService
import com.duke.protobuf.server.modules.game.net.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class ShopMessageFacade(
    val itemService: ItemService,
) {
    @MessageHandler(ItemPurchaseRequest::class)
    fun onPurchase(req: ItemPurchaseRequest, channel: Channel): ItemPurchaseResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return ItemPurchaseResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return ItemPurchaseResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        try {
            val res = itemService.buyItem(sessionChar, req.shopId, req.shopItemId)
            return ItemPurchaseResponse.newBuilder()
                .setResult(if (res.first == true) RESULT.SUCCESS else RESULT.FAILED)
                .setErrormsg(res.second ?: "购买成功。")
                .build()
        } catch (e: Exception) {
            logger.error("购买道具的过程中发生了异常！", e)
            return ItemPurchaseResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg(e.message ?: "")
                .build()
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ShopMessageFacade::class.java)
    }
}
