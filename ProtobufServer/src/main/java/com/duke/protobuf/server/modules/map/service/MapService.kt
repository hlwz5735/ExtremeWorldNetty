package com.duke.protobuf.server.modules.map.service

import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.DataDefineManager
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.map.model.GameMap
import com.duke.protobuf.server.net.pojo.OnlineUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.concurrent.ConcurrentHashMap

@Service
class MapService(
    private val dataDefineManager: DataDefineManager
): InitializingBean {
    /** 地图ID - 地图对象的映射表 */
    private val mapDic: MutableMap<Int, GameMap> = ConcurrentHashMap()

    override fun afterPropertiesSet() {
        this.dataDefineManager.gameMapDic.values.forEach { mapDefine ->
            logger.info("加载地图信息:{}:{}", mapDefine.id, mapDefine.name)
            this.mapDic[mapDefine.id] = GameMap(mapDefine)
        }
    }

    operator fun get(key: Int): GameMap? {
        return this.mapDic[key]
    }

    fun newCharacterEnter(mapId: Int, session: NettySession<OnlineUser>) {
        if (!this.mapDic.containsKey(mapId)) {
            throw RuntimeException("地图实例不存在！id=${mapId}")
        }
        val character = session.user.character ?:
            throw RuntimeException("用户尚未选择登录的角色。用户ID=${session.user.id}")
        this.mapDic[mapId]!!.playerEnter(character, session)
    }

    fun update() {
        this.mapDic.values.forEach(GameMap::update)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}