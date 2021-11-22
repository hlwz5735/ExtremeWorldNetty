package com.duke.protobuf.server.modules.game

import com.duke.protobuf.server.modules.game.entity.GameEntity
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

@Component
class GameEntityManager {
    /** 序号生成器 */
    private val idx = AtomicInteger()

    val entityList = CopyOnWriteArrayList<GameEntity>()
    val entityGroupByMap = ConcurrentHashMap<Int, MutableList<GameEntity>>(64)

    fun addToMap(mapId: Int, entity: GameEntity) {
        entityList.add(entity)
        entity.id = idx.incrementAndGet()

        if (entityGroupByMap[mapId] == null) {
            entityGroupByMap[mapId] = CopyOnWriteArrayList()
        }
        entityGroupByMap[mapId]?.add(entity)
    }

    fun removeFromMap(mapId: Int, entity: GameEntity) {
        entityList.remove(entity)
        entityGroupByMap[mapId]?.remove(entity)
    }
}