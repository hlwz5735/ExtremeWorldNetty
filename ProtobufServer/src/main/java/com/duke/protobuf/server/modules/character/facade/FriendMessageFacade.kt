package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.FriendService
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.user.OnlineUserManager
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class FriendMessageFacade(
    val service: FriendService,
    val onlineUserManager: OnlineUserManager,
) {
    @MessageHandler(FriendAddRequest::class)
    fun onFriendAddRequest(req: FriendAddRequest, channel: Channel): FriendAddResponse? {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()

        var toId: Int = req.toId
        if (req.toId == 0) {
            val target = onlineUserManager.getByName(req.toName)
                ?: return FriendAddResponse.newBuilder()
                    .setResult(RESULT.FAILED)
                    .setErrormsg("指定的朋友不存在或不在线上。")
                    .build()
            toId = target.character!!.dbId
        }

        val friend = service.getFriendByCharacterId(sessionChar.dbId, toId)
        if (friend != null) {
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("已经是好友关系了。")
                .build()
        }

        val onlineUser = onlineUserManager.getByCharacterId(toId)
        if (onlineUser?.character == null) {
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或不在线上。")
                .build()
        }

        logger.info("转发好友请求 :: FromId: {} FromName: {} toId: {} toName: {}", req.fromId, req.fromName, toId, req.toName)
        onlineUser.session.channel.writeAndFlush(
            NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddReq(FriendAddRequest.newBuilder(req).setToId(toId))
                )
                .build()
        )
        return null
    }

    @MessageHandler(FriendAddResponse::class)
    fun onFriendAddResponse(res: FriendAddResponse, channel: Channel): FriendAddResponse? {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        val sender = onlineUserManager.getByCharacterId(res.request.fromId)
            ?: return FriendAddResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("好友请求发起者已离线。")
                .build()
        if (res.result == RESULT.SUCCESS) {
            // 处理好友建立
            service.createFriendRelationship(sessionChar.dbId, sender.character!!.dbId)

            // 双方通知
            sender.session.channel.writeAndFlush(NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddRes(res))
                .build())
            return FriendAddResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setErrormsg("您和${sender.character!!.name}已成为好友")
                .build()
        } else {
            // 拒绝建立关系，转发响应
            sender.session.channel.writeAndFlush(NetMessage.newBuilder()
                .setResponse(NetMessageResponse.newBuilder()
                    .setFriendAddRes(res))
                .build())
            return null
        }
    }

    @MessageHandler(FriendListRequest::class)
    fun onFriendListRequest(req: FriendListRequest, channel: Channel): FriendListResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return FriendListResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return FriendListResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()

        val friends = service.listFriendsByCharacterId(sessionChar.dbId)

        return FriendListResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .addAllFriends(friends)
            .build()
    }

    @MessageHandler(FriendRemoveRequest::class)
    fun onFriendRemoveRequest(req: FriendRemoveRequest, channel: Channel): FriendRemoveResponse {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)
            ?: return FriendRemoveResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("用户会话不存在。")
                .build()
        val sessionChar = session.user.character
            ?: return FriendRemoveResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("指定的角色不存在或超出范围限制。")
                .build()
        service.removeRelationship(sessionChar.dbId, req.friendId)
        return FriendRemoveResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FriendMessageFacade::class.java.name)
    }
}
