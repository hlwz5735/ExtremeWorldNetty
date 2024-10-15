package com.duke.protobuf.server.modules.character.facade

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.character.service.TeamService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class TeamFacade(
    private val service: TeamService,
    private val onlineCharacterManager: OnlineCharacterManager,
) {
    @MessageHandler(TeamInviteRequest::class)
    fun onTeamInviteRequest(request: TeamInviteRequest, sessionChar: PlayerCharacter): TeamInviteResponse? {
        val toUser = onlineCharacterManager[request.toId]
            ?: return TeamInviteResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("好友当前不在线！")
                .build()
        if (toUser.character?.team != null) {
            return TeamInviteResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("好友尚未选择角色或已在队伍中！")
                .build()
        }

        // 转发请求
        toUser.session.sendSync { it.setTeamInviteReq(request) }
        return null
    }

    @MessageHandler(TeamInviteResponse::class)
    fun onTeamInviteResponse(respMsg: TeamInviteResponse, sessionChar: PlayerCharacter): TeamInviteResponse? {
        val fromUser = onlineCharacterManager[respMsg.request.fromId]
        if (respMsg.result == RESULT.SUCCESS) {
            // 若发起方已离线则通知响应方
            if (fromUser == null) {
                return TeamInviteResponse.newBuilder()
                    .setResult(RESULT.FAILED)
                    .setErrormsg("组队者已离线！")
                    .build()
            }
            service.addTeamMember(fromUser.character!!, sessionChar)
            // 将组队响应信息转发给发起方
            fromUser.session.sendSync { it.setTeamInviteRes(respMsg) }
        } else {
            // 不同意组队时只通知发起方，不通知响应方
            fromUser?.session?.sendSync { it.setTeamInviteRes(respMsg) }
        }

        return null
    }

    @MessageHandler(TeamLeaveRequest::class)
    fun onTeamLeave(request: TeamLeaveRequest, sessionChar: PlayerCharacter): TeamLeaveResponse? {
        if (sessionChar.team?.id != request.teamId) {
            logger.error("角色{}尝试退出一个本就不在里边的队伍(id: {})。", sessionChar.id, request.teamId)
            return null
        }
        sessionChar.team?.memberLeave(sessionChar)
        return TeamLeaveResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TeamFacade::class.java)
    }
}
