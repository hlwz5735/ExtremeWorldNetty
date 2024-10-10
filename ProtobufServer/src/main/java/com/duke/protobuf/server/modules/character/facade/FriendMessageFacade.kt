package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.FriendService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class FriendMessageFacade(
    val service: FriendService,
    val onlineCharacterManager: OnlineCharacterManager,
) {
    @MessageHandler(FriendAddRequest::class)
    fun onFriendAddRequest(req: FriendAddRequest, sessionChar: PlayerCharacter): FriendAddResponse? {
        var toId: Int = req.toId
        if (req.toId == 0) {
            val target = onlineCharacterManager.getByName(req.toName)
                ?: return FriendAddResponse.newBuilder()
                    .setResult(RESULT.FAILED)
                    .setErrormsg("指定的朋友不存在或不在线上。")
                    .build()
            toId = target.character!!.dbId
        }

        val friend = sessionChar.friendManager.getFriend(toId)
        if (friend != null) {
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("已经是好友关系了。")
                .build()
        }

        val onlineUser = onlineCharacterManager[toId]
        if (onlineUser?.character == null) {
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或不在线上。")
                .build()
        }

        logger.info("转发好友请求 :: FromId: {} FromName: {} toId: {} toName: {}", req.fromId, req.fromName, toId, req.toName)
        onlineUser.session.send(
            NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddReq(FriendAddRequest.newBuilder(req).setToId(toId))
                )
                .build()
        )
        return null
    }

    @MessageHandler(FriendAddResponse::class)
    fun onFriendAddResponse(res: FriendAddResponse, sessionChar: PlayerCharacter): FriendAddResponse? {
        val sender = onlineCharacterManager[res.request.fromId]
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("好友请求发起者已离线。")
                .build()
        if (res.result == RESULT.SUCCESS) {
            // 处理好友建立
            sessionChar.friendManager.addFriend(sender.character!!.dbId)

            // 双方通知
            sender.session.send(NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddRes(res))
                .build())
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setErrormsg("您和${sender.character!!.name}已成为好友")
                .build()
        } else {
            // 拒绝建立关系，转发响应
            sender.session.send(NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddRes(res))
                .build())
            return null
        }
    }

    @MessageHandler(FriendListRequest::class)
    fun onFriendListRequest(req: FriendListRequest, sessionChar: PlayerCharacter): FriendListResponse {
        return FriendListResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .addAllFriends(sessionChar.friendManager.nFriendList)
            .build()
    }

    @MessageHandler(FriendRemoveRequest::class)
    fun onFriendRemoveRequest(req: FriendRemoveRequest, sessionChar: PlayerCharacter): FriendRemoveResponse {
        sessionChar.friendManager.removeFriend(req.friendId)
        return FriendRemoveResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FriendMessageFacade::class.java.name)
    }
}
