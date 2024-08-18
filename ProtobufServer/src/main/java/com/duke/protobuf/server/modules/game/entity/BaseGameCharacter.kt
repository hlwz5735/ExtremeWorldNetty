package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.game.datadefine.CharacterDefine

open class BaseGameCharacter(
    entityId: Int = 0,
    var dbId: Int = 0,
    var type: CHARACTER_TYPE = CHARACTER_TYPE.UNRECOGNIZED,
    val tid: Int = 0,
    var level: Int = 1,
    var name: String = "",
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.NONE,
    var mapId: Int? = null,
    pos: Vector3Int,
    dir: Vector3Int
) : GameEntity(entityId, pos, dir) {
    lateinit var define: CharacterDefine

    open fun toNetCharacterInfo(): NCharacterInfo {
        return NCharacterInfo.newBuilder()
            // 本身的ID设为数据库ID
            .setId(dbId)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setTid(tid)
            .setName(this.name.ifEmpty { this.define.name })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .build()
    }
}
