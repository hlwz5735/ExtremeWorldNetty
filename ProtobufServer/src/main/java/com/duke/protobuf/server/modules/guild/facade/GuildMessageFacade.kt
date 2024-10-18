package com.duke.protobuf.server.modules.guild.facade

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.guild.manager.GuildManager
import com.duke.protobuf.server.modules.guild.service.GuildService
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class GuildMessageFacade(
    private val service: GuildService,
    private val guildManager: GuildManager,
    private val onlineCharacterManager: OnlineCharacterManager
) {
    @MessageHandler(GuildCreateRequest::class)
    fun onGuildCreate(req: GuildCreateRequest, sessionChar: PlayerCharacter): GuildCreateResponse? {
        if (sessionChar.guild != null) {
            return GuildCreateResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("当前角色已有工会在身！")
                .build()
        }
        if (service.checkNameExisted(req.guildName)) {
            return GuildCreateResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("工会名称已被占用！")
                .build()
        }
        try {
            val tGuild = service.createGuild(req.guildName, req.guildNotice, sessionChar)
            sessionChar.guild = guildManager[tGuild.id!!]
            return GuildCreateResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setGuildInfo(sessionChar.guild!!.getNGuildInfo(sessionChar))
                .build()
        } catch (e: Throwable) {
            logger.error("创建工会时发生了异常！", e)
            return GuildCreateResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("服务器异常！")
                .build()
        }
    }

    @MessageHandler(GuildListRequest::class)
    fun onGuildList(req: GuildListRequest, sessionChar: PlayerCharacter): GuildListResponse {
        val guildEntities = service.listAll()
        val guilds = guildEntities
            .mapNotNull {
                val guild = guildManager[it.id!!] ?: return@mapNotNull null
                return@mapNotNull guild.getNGuildInfo(null)
            }
            .toList()
        return GuildListResponse.newBuilder()
            .addAllGuilds(guilds)
            .build()
    }

    @MessageHandler(GuildJoinRequest::class)
    fun onGuildJoinReq(req: GuildJoinRequest, sessionChar: PlayerCharacter): GuildJoinResponse? {
        val guild = guildManager[req.applyInfo.guildId]
            ?: return GuildJoinResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("公会不存在")
                .build()
        val newApply = NGuildApplyInfo.newBuilder(req.applyInfo)
            .setCharacterId(sessionChar.dbId)
            .setName(sessionChar.name)
            .setClazz(sessionChar.clazz)
            .setLevel(sessionChar.level)
            .build()
        val applyRes = guild.joinApply(newApply)
        if (applyRes.first) {
            val leaderChar = onlineCharacterManager[guild.tableData.leaderId]
            leaderChar?.session?.sendAsync {
                it.setGuildJoinReq(GuildJoinRequest.newBuilder()
                    .setApplyInfo(newApply))
            }
        } else {
            return GuildJoinResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg(applyRes.second ?: "未知错误")
                .build()
        }
        return null
    }

    @MessageHandler(GuildJoinResponse::class)
    fun onGuildJoinRes(res: GuildJoinResponse, sessionChar: PlayerCharacter) {
        // 不应该发送 FAILED 消息
        if (res.result != RESULT.SUCCESS) return

        val guild = guildManager[res.applyInfo.guildId] ?: return
        val approved = res.applyInfo.result == NGuildApplyInfo.APPLY_RESULT.ACCEPTED
        val result = guild.checkJoin(res.applyInfo, approved)
        if (!result) {
            logger.warn("公会审批处理失败！【工会：{}-{}，申请人：{}-{}】这不应该发生！",
                guild.id, guild.tableData.name, res.applyInfo.characterId, res.applyInfo.name)
            return
        }

        // 通知申请人
        val requester = onlineCharacterManager[res.applyInfo.characterId] ?: return
        if (approved) {
            requester.character?.guild = guild
            requester.session.sendAsync {
                it.setGuildJoinRes(GuildJoinResponse.newBuilder()
                    .setResult(RESULT.SUCCESS)
                    .setErrormsg("加入公会成功。")
                )
            }
        } else {
            requester.session.sendAsync {
                it.setGuildJoinRes(GuildJoinResponse.newBuilder()
                    .setResult(RESULT.FAILED)
                    .setErrormsg("工会领导拒绝您加入工会。")
                )
            }
        }
    }

    @MessageHandler(GuildLeaveRequest::class)
    fun onGuildLeave(req: GuildLeaveRequest, sessionChar: PlayerCharacter): GuildLeaveResponse? {
        val guild = sessionChar.guild ?: return null
        val result = guild.memberLeave(sessionChar)
        if (result) {
            sessionChar.guild = null
            return GuildLeaveResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .build()
        } else {
            logger.warn("离开公会处理失败！【工会：{}-{}，待离开成员：{}-{}】这不应该发生！",
                guild.id, guild.tableData.name, sessionChar.dbId, sessionChar.name)
        }
        return null
    }

    @MessageHandler(GuildInfoRequest::class)
    fun onGuildInfo(req: GuildInfoRequest, sessionChar: PlayerCharacter): GuildInfoResponse {
        if (sessionChar.guild == null) {
            return GuildInfoResponse.newBuilder()
                .setResult(RESULT.FAILED)
                .setErrormsg("您还没有加入任何工会呢！")
                .build()
        }
        return GuildInfoResponse.newBuilder()
            .setResult(RESULT.SUCCESS)
            .setGuildInfo(sessionChar.guild!!.getNGuildInfo(sessionChar))
            .build()
    }

    @MessageHandler(GuildAdminRequest::class)
    fun onGuildAdmin(req: GuildAdminRequest, sessionChar: PlayerCharacter): GuildAdminResponse? {
        // 如果工会信息不存在却想管理工会，直接忽略
        if (sessionChar.guild == null) return null
        val result = sessionChar.guild!!.doAdmin(sessionChar, req)
        return GuildAdminResponse.newBuilder()
            .setResult(if (result.first) RESULT.SUCCESS else RESULT.FAILED)
            .setErrormsg(result.second ?: "操作成功")
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GuildMessageFacade::class.java)
    }
}
