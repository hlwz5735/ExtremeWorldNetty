package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.server.modules.game.core.Vector3Int

class Monster(
    val spawnMonId: Int,
    spawnLevel: Int,
    position: Vector3Int,
    direction: Vector3Int,
) : BaseGameCharacter(
    dbId = 0,
    configId = spawnMonId,
    entityId = 0,
    type = CHARACTER_TYPE.Monster,
    level = spawnLevel,
    name = "",
    clazz = CHARACTER_CLASS.NONE,
    mapId = null,
    pos = position,
    dir = direction,
) {
    init {
        name = tryGetDefine()?.name ?: "Unknown_Monster"
    }

    override fun toNetCharacterInfo(): NCharacterInfo {
        return NCharacterInfo.newBuilder()
            .setId(entityId)
            .setEntityId(this.entityId)
            .setConfigId(this.configId ?: 0)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setName(this.name.ifEmpty { tryGetDefine()?.name ?: "Unknown_Monster" })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .build()
    }
}
