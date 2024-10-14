package com.duke.protobuf.server.modules.map.service

import com.duke.protobuf.data.NEntitySync
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.server.modules.game.manager.GameEntityManager
import com.duke.protobuf.server.modules.game.datadefine.TeleporterDefine
import com.duke.protobuf.server.modules.game.entity.GameEntity
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.map.model.GameMap
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class MapService(
    private val dataDefineManager: DataDefineManager,
    private val gameEntityManager: GameEntityManager
): InitializingBean {
    /** 地图ID - 地图对象的映射表 */
    private val mapDic: MutableMap<Int, GameMap> = ConcurrentHashMap()
    var updateThreadRunning = false

    override fun afterPropertiesSet() {
        this.dataDefineManager.gameMapDic.values.forEach { mapDefine ->
            logger.info("加载地图信息:{}:{}", mapDefine.id, mapDefine.name)
            this.mapDic[mapDefine.id] = GameMap(mapDefine, gameEntityManager, dataDefineManager)
        }
        updateThreadRunning = true
        val updateThread = Thread({
            while (updateThreadRunning) {
                update()
                Thread.sleep(100)
            }
        })
        updateThread.start()
    }

    operator fun get(key: Int): GameMap? {
        return this.mapDic[key]
    }

    fun update() {
        this.mapDic.values.forEach(GameMap::update)
    }

    fun characterEnter(mapId: Int, session: NettySession<OnlineUser>) {
        val character = session.user.character
            ?: throw RuntimeException("用户尚未选择登录的角色。用户ID=${session.user.id}")
        this.mapDic[mapId]?.characterEnterMap(character, session)
    }

    fun characterLeave(mapId: Int, character: PlayerCharacter) {
        this.mapDic[mapId]?.characterLeaveMap(character)
    }

    fun updateEntity(mapId: Int, entity: GameEntity, entityEvent: NEntitySync.ENTITY_EVENT) {
        this.mapDic[mapId]?.updateEntity(entity, entityEvent)
    }

    /**
     * 根据传送点ID传送角色到指定位置
     */
    fun teleportCharacter(character: PlayerCharacter, teleportId: Int, session: NettySession<OnlineUser>) {
        if (!dataDefineManager.teleporterDic.containsKey(teleportId)) {
            logger.warn("ID为{}的传送点信息不存在！", teleportId)
            return
        }
        val sourceTeleporter: TeleporterDefine = dataDefineManager.teleporterDic[teleportId]!!
        if (sourceTeleporter.linkTo == 0 || !dataDefineManager.teleporterDic.containsKey(sourceTeleporter.linkTo)) {
            logger.warn("从传送点{}到传送点{}的传送点记录不存在！", sourceTeleporter.id, sourceTeleporter.linkTo)
            return
        }
        val targetTeleporter = dataDefineManager.teleporterDic[sourceTeleporter.linkTo]!!

        val sourceMap = mapDic[sourceTeleporter.mapId]!!
        sourceMap.characterLeaveMap(character)
        character.position = targetTeleporter.position!!
        character.direction = targetTeleporter.direction!!
        val targetMap = mapDic[targetTeleporter.mapId]!!
        targetMap.characterEnterMap(character, session)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MapService::class.java)
    }
}
