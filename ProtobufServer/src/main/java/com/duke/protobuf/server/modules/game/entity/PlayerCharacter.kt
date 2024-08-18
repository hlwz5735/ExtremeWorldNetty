package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.data.NItemInfo
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.item.service.ItemService
import com.duke.protobuf.server.modules.user.dbentity.TCharacter
import com.duke.protobuf.server.modules.user.manager.ItemManager

class PlayerCharacter(
    val tableData: TCharacter,
    itemService: ItemService,
    type: CHARACTER_TYPE = CHARACTER_TYPE.Player
) : BaseGameCharacter(
    entityId = 0,
    dbId = tableData.id!!,
    type,
    tableData.tid!!,
    1, // tableData.level
    tableData.name!!,
    tableData.clazz,
    tableData.mapId,
    pos = Vector3Int(tableData.mapPosX ?: 0, tableData.mapPosY ?: 0, tableData.mapPosZ ?: 0),
    dir = Vector3Int(100, 0, 0)
) {
    val itemManager: ItemManager = ItemManager(this, itemService)

    override fun toNetCharacterInfo(): NCharacterInfo {
        val items = itemManager.buildItemInfos()
        val builder = NCharacterInfo.newBuilder()
            // 本身的ID设为数据库ID
            .setId(dbId)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setTid(tid)
            .setName(this.name.ifEmpty { this.define.name })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .addAllItems(items)
        return builder.build()
    }
}
