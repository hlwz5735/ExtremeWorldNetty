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
    entityId = 0,
    dbId = 0,
    type = CHARACTER_TYPE.Monster,
    level = spawnLevel,
    name = "怪物",
    clazz = CHARACTER_CLASS.NONE,
    mapId = null,
    tid = spawnMonId,
    pos = position,
    dir = direction,
) {
    override fun toNetCharacterInfo(): NCharacterInfo {
        return NCharacterInfo.newBuilder()
            // 本身的ID设为实体ID
            .setId(id)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setTid(this.tid ?: 0)
            .setName(this.name.ifEmpty { this.define.name })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .build()
    }
}
