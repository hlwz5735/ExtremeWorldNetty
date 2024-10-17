package com.duke.protobuf.server.modules.guild.service

import com.duke.protobuf.data.ProtoMessages.NCharacterInfo
import com.duke.protobuf.data.ProtoMessages.NGuildApplyInfo
import com.duke.protobuf.data.ProtoMessages.NGuildInfo
import com.duke.protobuf.data.ProtoMessages.NGuildMemberInfo
import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.guild.dbentity.TGuild
import com.duke.protobuf.server.modules.guild.dbentity.TGuildApply
import com.duke.protobuf.server.modules.guild.dbentity.TGuildMember
import com.duke.protobuf.server.modules.guild.model.Guild
import com.duke.protobuf.server.modules.guild.repo.GuildApplyRepository
import com.duke.protobuf.server.modules.guild.repo.GuildMemberRepository
import com.duke.protobuf.server.modules.guild.repo.GuildRepository
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import com.duke.protobuf.server.util.DateTimeUtil
import com.duke.protobuf.structure.DTuple
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class GuildService(
    private val repo: GuildRepository,
    private val memberRepo: GuildMemberRepository,
    private val applyRepo: GuildApplyRepository,
    private val characterRepo: CharacterRepository,
    private val onlineCharacterManager: OnlineCharacterManager,
) {
    @Transactional
    fun createGuild(guildName: String, guildNotice: String, sessionChar: PlayerCharacter): TGuild {
        val entity = TGuild(
            name = guildName,
            leaderId = sessionChar.dbId,
            leaderName = sessionChar.name,
            notice = guildNotice,
        )
        repo.save(entity)
        val memberEntity = TGuildMember(
            characterId = sessionChar.dbId,
            name = sessionChar.name,
            clazz = sessionChar.clazz,
            level = sessionChar.level,
            title = NGuildMemberInfo.GUILD_TITLE.PRESIDENT,
            owner = entity
        )
        memberRepo.save(memberEntity)
        return entity
    }

    @Transactional
    fun addApply(info: NGuildApplyInfo, owner: TGuild): DTuple<Boolean, String?> {
        val guildId = info.guildId
        val requesterId = info.characterId
        val entry = applyRepo.getByOwnerIdAndCharacterId(guildId, requesterId)
        if (entry == null) {
            val newEntity = TGuildApply(
                characterId = info.characterId,
                name = info.name,
                clazz = info.clazz,
                level = info.level,
                owner = owner,
            )
            applyRepo.save(newEntity)
            return DTuple(true, null)
        } else {
            if (entry.result != NGuildApplyInfo.APPLY_RESULT.REJECTED) {
                return DTuple(false, "申请记录已存在！")
            } else {
                entry.applyTime = LocalDateTime.now()
                entry.result = NGuildApplyInfo.APPLY_RESULT.NONE
                applyRepo.save(entry)
                return DTuple(true, null)
            }
        }
    }

    /** 加入审批 */
    @Transactional
    fun checkJoin(info: NGuildApplyInfo, owner: TGuild, approved: Boolean): Boolean {
        val guildId = info.guildId
        val requesterId = info.characterId
        val entry = applyRepo.getByOwnerIdAndCharacterId(guildId, requesterId) ?: return false
        if (approved) {
            entry.result = NGuildApplyInfo.APPLY_RESULT.ACCEPTED

            val member = TGuildMember(
                characterId = info.characterId,
                name = info.name,
                clazz = info.clazz,
                level = info.level,
                owner = owner,
            )
            memberRepo.save(member)
        } else {
            entry.result = NGuildApplyInfo.APPLY_RESULT.REJECTED
        }
        applyRepo.save(entry)
        return true
    }

    /** 成员离开 */
    @Transactional
    fun memberLeave(member: PlayerCharacter, guild: Guild): Boolean {
        val memberEntry = memberRepo.getByCharacterId(member.id)
        if (memberEntry == null) {
            logger.warn("用户要离开工会{}，但实际上没有查到角色的工会所属信息！", guild.id)
            return false
        }
        if (memberEntry.owner!!.id != guild.id) {
            logger.warn("用户要离开的工会{}和实际工会{}不一致！", guild.id, memberEntry.owner!!.id)
            return false
        }
        memberRepo.delete(memberEntry)
        return true
    }

    @Transactional(readOnly = true)
    fun buildMemberInfo(member: TGuildMember): NGuildMemberInfo {
        val result = NGuildMemberInfo.newBuilder()
            .setId(member.id!!)
            .setCharacterId(member.characterId)
            .setTitle(member.title)
            .setJoinTime(DateTimeUtil.localDateTimeToUnixTimestamp(member.joinTime))
            .setLastTime(DateTimeUtil.localDateTimeToUnixTimestamp(member.lastTime))
        val c = onlineCharacterManager[member.characterId]
        if (c?.character != null) {
            result
                .setStatus(1)
                .setInfo(c.character!!.getBasicInfo())
        } else {
            val tc = characterRepo.findById(member.characterId).get()
            result
                .setStatus(0)
                .setInfo(NCharacterInfo.newBuilder()
                    .setId(tc.id!!)
                    .setClass_(tc.clazz)
                    .setLevel(tc.level)
                    .setName(tc.name)
                    .build())
        }
        return result.build()
    }

    @Transactional(readOnly = true)
    fun checkNameExisted(guildName: String): Boolean {
        val count = repo.countByName(guildName)
        return count > 0
    }

    @Transactional(readOnly = true)
    fun getCharacterGuild(characterId: Int): TGuild? {
        return memberRepo.getByCharacterId(characterId)?.owner
    }

    @Transactional(readOnly = true)
    fun getById(guildId: Int): TGuild? {
        return repo.findById(guildId).orElse(null)
    }

    @Transactional(readOnly = true)
    fun listAll(): List<TGuild> {
        return repo.findAll()
    }

    @Transactional(readOnly = true)
    fun listMembers(guildId: Int): List<TGuildMember> {
        return memberRepo.queryByOwnerId(guildId)
    }

    @Transactional(readOnly = true)
    fun listApplies(guildId: Int): List<TGuildApply> {
        return applyRepo.queryByOwnerId(guildId)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GuildService::class.java)
    }
}
