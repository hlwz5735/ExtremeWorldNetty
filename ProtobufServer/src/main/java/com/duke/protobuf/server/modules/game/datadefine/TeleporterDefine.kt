package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.server.modules.game.core.Vector3Int

data class TeleporterDefine(
    val id: Int,
    val name: String,
    val mapId: Int,
    val linkTo: Int,
    val description: String?,
    val position: Vector3Int?,
    val direction: Vector3Int?,
)
