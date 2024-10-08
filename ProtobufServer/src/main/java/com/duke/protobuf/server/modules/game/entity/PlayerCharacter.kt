package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.data.NBagInfo
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.character.service.ItemService
import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import com.duke.protobuf.server.modules.character.manager.ItemManager
import com.duke.protobuf.server.modules.character.manager.QuestManager
import com.duke.protobuf.server.modules.character.manager.StatusManager
import com.duke.protobuf.server.modules.character.service.BagService
import com.duke.protobuf.server.modules.character.service.CharacterService
import com.duke.protobuf.server.modules.character.service.QuestService
import com.google.protobuf.ByteString

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
    type,
    tableData.level,
    tableData.name!!,
    tableData.clazz,
    tableData.mapId,
    // TODO: 暂时角色TID根据职业转换
    tid = tableData.clazz.number,
    pos = Vector3Int(tableData.mapPosX ?: 0, tableData.mapPosY ?: 0, tableData.mapPosZ ?: 0),
    dir = Vector3Int(100, 0, 0)
) {
    val itemManager = ItemManager(this, itemService)
    val statusManager = StatusManager(this, itemService)
    val questManager = QuestManager(this, questService, service)

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
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setName(this.name.ifEmpty { this.define.name })
            .setMapId(this.mapId ?: 0)
            .setTid(this.tid ?: 0)
            .setEntity(this.toNetEntity())
            .setBag(nBag)
            .setEquips(equipBs)
            .setCarriedMoney(tableData.carriedMoney)
            .addAllItems(nItems)
            .addAllQuests(nQuests)
        return builder.build()
    }
}
