package com.duke.protobuf.server.modules.map.manager

import com.duke.protobuf.server.modules.game.core.Vector3Int
import com.duke.protobuf.server.modules.game.entity.Monster
import com.duke.protobuf.server.modules.game.manager.GameEntityManager
import com.duke.protobuf.server.modules.map.model.GameMap

class MonsterManager(
    private val map: GameMap,
    private val entityManager: GameEntityManager,
) {
    val monsterDic: MutableMap<Int, Monster> = HashMap()

    fun createMonster(spawnMonId: Int, spawnLevel: Int, position: Vector3Int, direction: Vector3Int): Monster {
        val monster = Monster(spawnMonId, spawnLevel, position, direction)
        entityManager.addToMap(this.map.id, monster)
        monster.mapId = this.map.id
        monsterDic[monster.id] = monster
        this.map.monsterEnter(monster)
        return monster
    }
}
