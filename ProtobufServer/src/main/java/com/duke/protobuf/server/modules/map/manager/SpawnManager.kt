package com.duke.protobuf.server.modules.map.manager

import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.server.modules.map.model.GameMap
import com.duke.protobuf.server.modules.map.model.Spawner

class SpawnManager(
    private val map: GameMap,
    dataDefineManager: DataDefineManager,
) {
    private val spawners: List<Spawner> = dataDefineManager.spawnRuleDic[map.id]
        ?.map { Spawner(it.value, map, dataDefineManager) }
        ?.toList() ?: emptyList()

    fun update() {
        spawners.forEach { it.update() }
    }
}
