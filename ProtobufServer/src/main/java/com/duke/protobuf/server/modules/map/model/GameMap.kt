package com.duke.protobuf.server.modules.map.model

import com.duke.protobuf.data.*
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.manager.GameEntityManager
import com.duke.protobuf.server.modules.game.datadefine.MapDefine
import com.duke.protobuf.server.modules.game.entity.GameEntity
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class GameMap(
    internal val define: MapDefine,
    private val gameEntityManager: GameEntityManager
) {

    /** 当前地图中的角色列表，键为角色的ID */
    private val characterMap = ConcurrentHashMap<Int, CharacterWithSession>()

    val id get() = define.id

    internal fun playerEnter(character: PlayerCharacter, session: NettySession<OnlineUser>) {
        // 在正式发出数据前，给实体ID赋值
        this.gameEntityManager.addToMap(this.id, character)

        logger.info("playerEnter: Map:{} characterId:{}", this.id, character.id)
        character.mapId = this.id

        // 向其他玩家角色发送角色进入的消息
        val responseForOther = MapCharacterEnterResponse.newBuilder()
            .setMapId(id)
            // 只包含当前用户信息
            .addCharacters(character.toNetCharacterInfo())
            .build()
        this.characterMap.values.forEach {
            it.session.channel.writeAndFlush(buildCharEnterResponse(responseForOther))
        }

        this.characterMap[character.id] = CharacterWithSession(session, character)
        val characterList = this.characterMap.values
            .map { it.character.toNetCharacterInfo() }
            .toList()

        val response = buildCharEnterResponse(MapCharacterEnterResponse.newBuilder()
            .setMapId(this.id)
            .addAllCharacters(characterList)
            .build())

        session.channel.writeAndFlush(response)
    }

    private fun buildCharEnterResponse(obj: MapCharacterEnterResponse): NetMessage {
        val builder = NetMessage.newBuilder()
        builder.responseBuilder.mapCharacterEnter = obj
        return builder.build()
    }

    fun playerLeave(character: PlayerCharacter) {
        logger.info("playerLeave: Map:{} characterId:{}", this.id, character.id)
        this.gameEntityManager.removeFromMap(this.id, character)

        character.mapId = null

        val response = buildCharLeaveResponse(MapCharacterLeaveResponse.newBuilder()
            .setCharacterId(character.id)
            .build())

        this.characterMap.values.forEach {
            it.session.channel.writeAndFlush(response)
        }
        this.characterMap.remove(character.id)
    }

    private fun buildCharLeaveResponse(obj: MapCharacterLeaveResponse): NetMessage {
        val builder = NetMessage.newBuilder()
        builder.responseBuilder.mapCharacterLeave = obj
        return builder.build()
    }

    internal fun update() {
        throw NotImplementedError()
    }

    /**
     * 更新地图内的实体信息
     */
    fun updateEntity(gameEntity: GameEntity, entityEvent: NEntitySync.ENTITY_EVENT) {
        val entityId = gameEntity.id

        val responseForOther = buildMapEntitySyncResponse(
            MapEntitySyncResponse
                .newBuilder()
                .addEntitySyncs(
                    NEntitySync
                        .newBuilder()
                        .setId(gameEntity.id)
                        .setEvent(entityEvent)
                        .setEntity(gameEntity.toNetEntity())
                        .build()
                )
                .build()
        )

        this.characterMap.values.forEach {
            val character = it.character
            if (character.id == entityId) {
                character.position = gameEntity.position
                character.direction = gameEntity.direction
                character.speed = gameEntity.speed
            } else {
                it.session.channel.writeAndFlush(responseForOther)
            }
        }
    }

    private fun buildMapEntitySyncResponse(obj: MapEntitySyncResponse): NetMessage {
        val builder = NetMessage.newBuilder()
        builder.responseBuilder.mapEntitySync = obj
        return builder.build()
    }

    inner class CharacterWithSession (
        val session: NettySession<OnlineUser>,
        val character: PlayerCharacter
    )

    companion object {
        private val logger = LoggerFactory.getLogger(GameMap::class.java)
    }
}
