package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.*
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import com.duke.protobuf.server.modules.character.manager.FriendManager
import com.duke.protobuf.server.modules.character.manager.ItemManager
import com.duke.protobuf.server.modules.character.manager.QuestManager
import com.duke.protobuf.server.modules.character.manager.StatusManager
import com.duke.protobuf.server.modules.character.service.*
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import com.duke.protobuf.server.util.SpringContextUtil
import com.google.protobuf.ByteString
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class PlayerCharacter(
    val tableData: TCharacter,
    service: CharacterService,
    itemService: ItemService,
    questService: QuestService,
    private val bagService: BagService,
    type: CHARACTER_TYPE = CHARACTER_TYPE.Player
) : BaseGameCharacter(
    entityId = 0,
    dbId = tableData.id!!,
    // TODO: 暂时角色TID根据职业转换
    configId = tableData.clazz.number,
    type,
    tableData.level,
    tableData.name!!,
    tableData.clazz,
    tableData.mapId,
    pos = Vector3Int(tableData.mapPosX ?: 0, tableData.mapPosY ?: 0, tableData.mapPosZ ?: 0),
    dir = Vector3Int(100, 0, 0)
) {
    val itemManager = ItemManager(this, itemService)
    val statusManager = StatusManager(this, itemService)
    val questManager = QuestManager(this, questService, service)
    val friendManager = FriendManager(this, SpringContextUtil.getBean(OnlineCharacterManager::class.java)!!)

    private val lock = ReentrantLock()

    private var responseBuilder: NetMessageResponse.Builder? = null

    fun getResponseBuilder(): NetMessageResponse.Builder {
        if (responseBuilder == null) {
            lock.withLock {
                if (responseBuilder == null) {
                    responseBuilder = NetMessageResponse.newBuilder()
                }
            }
        }
        return responseBuilder!!
    }

    fun resetResponseBuilder() {
        responseBuilder = null
    }

    fun postResponseProcess() {
        // 状态变更
        val statusNotifies = statusManager.collectChangesToResponse()
        getResponseBuilder().setStatusNotify(statusNotifies)
        // 好友状态
        if (friendManager.isDirty) {
            getResponseBuilder().setFriendList(
                FriendListResponse.newBuilder()
                    .addAllFriends(friendManager.nFriendList))
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

        val builder = NCharacterInfo.newBuilder()
            // 本身的ID设为数据库ID
            .setId(dbId)
            .setConfigId(this.configId ?: 0)
            .setEntityId(this.entityId)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            // .setExp(tableData.exp)
            .setName(this.name.ifEmpty { tryGetDefine()?.name ?: "Unknown_Char" })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .setBag(nBag)
            .setEquips(equipBs)
            .setCarriedMoney(tableData.carriedMoney)
            .addAllItems(nItems)
            .addAllQuests(nQuests)
            .addAllFriends(this.friendManager.nFriendList)
        return builder.build()
    }
}
