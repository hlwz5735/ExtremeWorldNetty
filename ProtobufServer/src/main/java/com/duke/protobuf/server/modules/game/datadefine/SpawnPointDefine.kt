package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.server.modules.game.core.Vector3Int

data class SpawnPointDefine(
    val id: Int,
    val mapId: Int,
    val position: Vector3Int,
    val direction: Vector3Int,
)
