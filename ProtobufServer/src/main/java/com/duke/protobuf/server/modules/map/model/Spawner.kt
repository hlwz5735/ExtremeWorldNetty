package com.duke.protobuf.server.modules.map.model

import com.duke.protobuf.server.modules.game.datadefine.SpawnPointDefine
import com.duke.protobuf.server.modules.game.datadefine.SpawnRuleDefine
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import org.slf4j.LoggerFactory

class Spawner(
    private val define: SpawnRuleDefine,
    private val map: GameMap,
    dataDefineManager: DataDefineManager,
) {
    /** 上次刷怪时间 */
    private var spawnTime = 0L
    /** 上次生成的怪物被杀掉的时间 */
    var unspawnTime = 0L
    /** 刷怪点是否刷过怪物且怪物还活着 */
    var spawned = false

    private val spawnPoint: SpawnPointDefine?

    init {
        unspawnTime = System.currentTimeMillis()
        if (dataDefineManager.spawnPointDic.containsKey(map.id)) {
            this.spawnPoint = dataDefineManager.spawnPointDic[map.id]!![define.spawnPoint]
        } else {
            logger.warn("SpawnRule[{}] SpawnPoint[{}] not existed.", define.id, define.spawnPoint)
            this.spawnPoint = null
        }
    }

    fun update() {
        if (canSpawn()) {
            spawn()
        }
    }

    private fun canSpawn(): Boolean {
        if (spawned) {
            return false
        }
        if (unspawnTime + define.spawnPeriod * 1000 > System.currentTimeMillis()) {
            return false
        }
        return true
    }

    private fun spawn() {
        if (spawnPoint == null) {
            return
        }

        logger.info("Map[{}] Spawn[{}:Mon:{},Lv:{}] at Point:({}, {}, {})",
            define.mapId, define.id, define.spawnMonId, define.spawnLevel,
            spawnPoint.position.x, spawnPoint.position.y, spawnPoint.position.z)

        spawned = true
        spawnTime = System.currentTimeMillis()
        map.monsterManager.createMonster(
            define.spawnMonId,
            define.spawnLevel,
            spawnPoint.position,
            spawnPoint.direction
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Spawner::class.java)
    }
}
