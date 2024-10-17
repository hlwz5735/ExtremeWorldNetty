package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import com.duke.protobuf.server.modules.character.manager.FriendManager
import com.duke.protobuf.server.modules.character.manager.ItemManager
import com.duke.protobuf.server.modules.character.manager.QuestManager
import com.duke.protobuf.server.modules.character.manager.StatusManager
import com.duke.protobuf.server.modules.character.model.Team
import com.duke.protobuf.server.modules.character.service.BagService
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.guild.manager.GuildManager
import com.duke.protobuf.server.modules.guild.model.Guild
import com.duke.protobuf.server.util.SpringContextUtil
import com.google.protobuf.ByteString

class PlayerCharacter(
    val tableData: TCharacter,
    type: CHARACTER_TYPE = CHARACTER_TYPE.Player
) : BaseGameCharacter(
    entityId = 0,
    dbId = tableData.id!!,
    // TODO: 暂时角色TID根据职业转换
    configId = tableData.clazz.number,
    type,
    tableData.level,
    tableData.name,
    tableData.clazz,
    tableData.mapId,
    pos = Vector3Int(tableData.mapPosX ?: 0, tableData.mapPosY ?: 0, tableData.mapPosZ ?: 0),
    dir = Vector3Int(100, 0, 0)
) {
    private val bagService = SpringContextUtil.getBean(BagService::class.java)!!
    private val guildManager = SpringContextUtil.getBean(GuildManager::class.java)!!

    lateinit var session: NettySession<OnlineUser>
    val itemManager = ItemManager(this)
    val statusManager = StatusManager(this)
    val questManager = QuestManager(this)
    val friendManager = FriendManager(this)

    var team: Team? = null
    private var teamUpdateTs = 0L
    var guild: Guild? = null
        set(value) {
            field = value
            guildUpdateTs = 0
        }
    private var guildUpdateTs = 0L

    init {
        guild = guildManager.getCharacterGuild(this.dbId)
        guildUpdateTs = guild?.timestamp ?: 0
    }

    fun postResponseProcess() {
        session.sendAsync {
            // 状态变更
            val statusNotifies = statusManager.collectChangesToResponse()
            if (statusNotifies != null) {
                it.setStatusNotify(statusNotifies)
            }
            // 好友状态
            if (friendManager.isDirty) {
                it.setFriendList(
                    FriendListResponse.newBuilder()
                        .addAllFriends(friendManager.nFriendList)
                )
                friendManager.isDirty = false
            }
        }
        val team = this.team
        if (team != null) {
            if (teamUpdateTs < team.timestamp) {
                teamUpdateTs = team.timestamp
                team.postProcess(session)
            }
        }
        val guild = this.guild
        if (guild != null) {
            if (guildUpdateTs < guild.timestamp) {
                guildUpdateTs = guild.timestamp
                guild.postProcess(session)
            }
        } else {
            if (guildUpdateTs > 0) {
                guildUpdateTs = 0
            }
        }
    }

    override fun toNetCharacterInfo(): NCharacterInfo {
        // 构建道具信息
        val nItems = itemManager.buildItemInfos()
        // 构建背包信息
        val tBag = bagService.getByCharacterId(dbId)
        val itemsBs = ByteString.copyFrom(tBag.items ?: byteArrayOf())
        val equipBs = ByteString.copyFrom(tableData.equips ?: byteArrayOf())
        val nBag = NBagInfo.newBuilder()
            .setUnlocked(tBag.unlockedCellCount ?: 1)
            .setItems(itemsBs)
        // 构建任务信息
        val nQuests = questManager.buildQuestInfos()
        // 构建好友信息
        val nFriends = this.friendManager.nFriendList
        // 构建工会信息
        val nGuild = this.guild?.getNGuildInfo(this)

        val builder = NCharacterInfo.newBuilder()
            // 本身的ID设为数据库ID
            .setId(dbId)
            .setConfigId(this.configId ?: 0)
            .setEntityId(this.entityId)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            // .setExp(tableData.exp)
            .setName(this.name)
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .setBag(nBag)
            .setEquips(equipBs)
            .setCarriedMoney(tableData.carriedMoney)
            .addAllItems(nItems)
            .addAllQuests(nQuests)
            .addAllFriends(nFriends)
        if (nGuild != null) {
            builder.setGuild(nGuild)
        }
        return builder.build()
    }

    fun getBasicInfo(): NCharacterInfo {
        return NCharacterInfo.newBuilder()
            .setId(dbId)
            .setClass_(this.clazz)
            .setLevel(level)
            .setName(this.name)
            .build()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this.javaClass != other.javaClass) return false
        other as PlayerCharacter

        return id != null && id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
