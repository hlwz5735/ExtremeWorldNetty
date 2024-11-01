package com.duke.protobuf.server.modules.guild.model

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.guild.dbentity.TGuild
import com.duke.protobuf.server.modules.guild.dbentity.TGuildApply
import com.duke.protobuf.server.modules.guild.dbentity.TGuildMember
import com.duke.protobuf.server.modules.guild.manager.GuildManager
import com.duke.protobuf.server.modules.guild.service.GuildService
import com.duke.protobuf.server.util.DateTimeUtil
import com.duke.protobuf.server.util.SpringContextUtil
import com.duke.protobuf.structure.DTuple

class Guild(
    val tableData: TGuild
) {
    private val service = SpringContextUtil.getBean(GuildService::class.java)!!
    private val manager = SpringContextUtil.getBean(GuildManager::class.java)!!

    var timestamp: Long = System.currentTimeMillis()

    val id get() = tableData.id!!
    private var members: List<TGuildMember>
    private var applies: List<TGuildApply>

    init {
        members = service.listMembers(id)
        applies = service.listApplies(id)
    }

    fun joinApply(info: NGuildApplyInfo): DTuple<Boolean, String?> {
        val res = service.addApply(info, tableData)
        if (res.first) {
            applies = service.listApplies(id)
            timestamp = System.currentTimeMillis()
        }
        return res
    }

    /** 加入审批 */
    fun checkJoin(applyInfo: NGuildApplyInfo, approved: Boolean): Boolean {
        val res = service.checkJoin(applyInfo, tableData, approved)
        if (res) {
            members = service.listMembers(id)
            applies = service.listApplies(id)
            timestamp = System.currentTimeMillis()
        }
        return res
    }

    fun memberLeave(member: PlayerCharacter): Boolean {
        if (member.dbId == tableData.leaderId) {
            return false
        }
        val res = service.memberLeave(member, this)
        if (res) {
            members = service.listMembers(id)
            timestamp = System.currentTimeMillis()
        }
        return res
    }

    /** 执行管理方法 */
    fun doAdmin(operatorChar: PlayerCharacter, req: GuildAdminRequest): DTuple<Boolean, String?> {
        val operator = members.find { it.characterId == operatorChar.dbId } ?: return DTuple(false, "您无权操作本公会！")
        if (operator.title != NGuildMemberInfo.GUILD_TITLE.PRESIDENT
            && operator.title != NGuildMemberInfo.GUILD_TITLE.VICE_PRESIDENT
        ) {
            return DTuple(false, "您无权操作！")
        }

        val targetMember = members.find { it.characterId == req.target } ?: return DTuple(false, "并未指定目标用户！")
        if (operator.characterId == targetMember.characterId) {
            return DTuple(false, "不能对自己操作！")
        }

        val result: DTuple<Boolean, String?> = when (req.command) {
            GuildAdminRequest.GUILD_ADMIN_COMMAND.DEPORT -> service.deport(this.tableData, operator, targetMember)
            GuildAdminRequest.GUILD_ADMIN_COMMAND.GRANT -> service.grant(this.tableData, operator, targetMember)
            GuildAdminRequest.GUILD_ADMIN_COMMAND.RELIVE -> service.relive(this.tableData, operator, targetMember)
            GuildAdminRequest.GUILD_ADMIN_COMMAND.CEDE -> service.cede(this.tableData, operator, targetMember)
            GuildAdminRequest.GUILD_ADMIN_COMMAND.EDIT_NOTICE -> service.updateNotice(this.tableData, operator, req.notice)
            else -> DTuple(false, "不支持的命令。")
        }
        if (result.first) {
            members = service.listMembers(id)
            applies = service.listApplies(id)
            timestamp = System.currentTimeMillis()
        }
        return result
    }

    fun postProcess(session: NettySession<OnlineUser>) {
        // 发送简化版的工会信息
        session.sendLazy {
            it.setGuildInfo(GuildInfoResponse.newBuilder()
                .setGuildInfo(getNGuildInfo(session.user.character!!))
            )
        }
    }

    fun getNGuildInfo(from: PlayerCharacter?): NGuildInfo {
        val info = NGuildInfo.newBuilder()
            .setId(tableData.id!!)
            .setGuildName(tableData.name)
            .setLeaderId(tableData.leaderId)
            .setLeaderName(tableData.leaderName)
            .setNotice(tableData.notice)
            .setMemberCount(members.size)
            .setCreateTime(DateTimeUtil.localDateTimeToUnixTimestamp(tableData.createTime))
        // 如果传来源，返回基本信息
        if (from == null) {
            return info.build()
        }
        // 如果不是成员，同样返回基本信息
        val memberInfo = members.find { it.characterId == from.dbId } ?: return info.build()
        info.addAllMembers(members.map(service::buildMemberInfo).toList())
        // 如果是会长/副会长，填充申请信息
        if (memberInfo.title == NGuildMemberInfo.GUILD_TITLE.PRESIDENT
            || memberInfo.title == NGuildMemberInfo.GUILD_TITLE.VICE_PRESIDENT
        ) {
            info.addAllApplies(applies
                .filter { it.result == NGuildApplyInfo.APPLY_RESULT.NONE }
                .map {
                    NGuildApplyInfo.newBuilder()
                        .setGuildId(tableData.id!!)
                        .setCharacterId(it.characterId)
                        .setName(it.name)
                        .setClazz(it.clazz)
                        .setLevel(it.level)
                        .setResult(it.result)
                        .build()
                }.toList()
            )
        }
        return info.build()
    }
}
