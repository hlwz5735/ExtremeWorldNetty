package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.CHARACTER_TYPE
import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.user.dbentity.TCharacter

class PlayerCharacter(
    var tableData: TCharacter,
    type: CHARACTER_TYPE = CHARACTER_TYPE.UNRECOGNIZED
) : BaseGameCharacter(
    tableData.id!!,
    type,
    tableData.tid!!,
    1, // tableData.level
    tableData.name!!,
    tableData.clazz,
    tableData.mapId,
    pos = Vector3Int(tableData.mapPosX ?: 0, tableData.mapPosY ?: 0, tableData.mapPosZ ?: 0),
    dir = Vector3Int(100, 0, 0)
) {

}
