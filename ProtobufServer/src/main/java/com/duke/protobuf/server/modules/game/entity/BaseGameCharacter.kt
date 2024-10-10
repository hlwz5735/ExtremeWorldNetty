package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.game.datadefine.CharacterDefine
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.server.util.SpringContextUtil

open class BaseGameCharacter(
    entityId: Int = 0,
    var dbId: Int = 0,
    /** 配置表中的ID */
    var configId: Int? = null,
    var type: CHARACTER_TYPE = CHARACTER_TYPE.UNRECOGNIZED,
    var level: Int = 1,
    var name: String = "",
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.NONE,
    var mapId: Int? = null,
    pos: Vector3Int,
    dir: Vector3Int
) : GameEntity(entityId, pos, dir) {
    open fun toNetCharacterInfo(): NCharacterInfo {
        return NCharacterInfo.newBuilder()
            // 本身的ID设为数据库ID
            .setId(dbId)
            .setConfigId(configId ?: 0)
            .setEntityId(this.entityId)
            .setType(type)
            .setClass_(this.clazz)
            .setLevel(level)
            .setName(this.name.ifEmpty { tryGetDefine()?.name ?: "Unknown_Char" })
            .setMapId(this.mapId ?: 0)
            .setEntity(this.toNetEntity())
            .build()
    }

    protected fun tryGetDefine(): CharacterDefine? {
        val dataDefineManager = SpringContextUtil.getBean(DataDefineManager::class.java)
        return dataDefineManager?.characterDic?.get(configId)
    }
}
