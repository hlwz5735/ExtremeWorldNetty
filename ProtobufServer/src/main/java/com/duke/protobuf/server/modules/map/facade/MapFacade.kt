package com.duke.protobuf.server.modules.map.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.game.entity.GameEntity
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.map.service.MapService
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@MessageFacade
@Component
class MapFacade(
    private val service: MapService,
) {

    @MessageHandler(MapEntitySyncRequest::class)
    fun onMapEntitySync(request: MapEntitySyncRequest, character: PlayerCharacter) {
        val sync = request.entitySync ?: return
        val mapId = character.mapId ?: return

        logger.debug("地图游戏实体对象更新请求：角色id：{}，实体id：{}，事件：{}，地图id：{}",
            character.id, sync.id, sync.event, mapId)

        // 从网络数据构建游戏实体对象并进行同步
        val entity = GameEntity(sync.entity)
        this.service.updateEntity(mapId, entity, sync.event)
    }

    @MessageHandler(MapTeleportRequest::class)
    fun onMapTeleport(request: MapTeleportRequest, channel: Channel) {
        val session = SessionUtil.getSessionByChannel<OnlineUser>(channel)!!
        val character = session.user.character ?: return
        val teleportId = request.teleporterId

        logger.info("地图传送请求：角色id：{}，传送点id：{}", character.id, teleportId)

        this.service.teleportCharacter(character, teleportId, session)
    }

    companion object { private val logger = LoggerFactory.getLogger(MapFacade::class.java) }
}
