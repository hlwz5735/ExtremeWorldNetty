package com.duke.protobuf.server.modules.game.datadefine

data class SpawnRuleDefine(
    val id: Int,
    val mapId: Int,
    val spawnMonId: Int,
    val spawnLevel: Int,
    val spawnPeriod: Int,
    val spawnPoint: Int,
    val remark: String? = null,
)
