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

    /** 游戏实体对象列表 */
    val entityList = CopyOnWriteArrayList<GameEntity>()
    /** 按地图分组的游戏实体对象列表键值对 */
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