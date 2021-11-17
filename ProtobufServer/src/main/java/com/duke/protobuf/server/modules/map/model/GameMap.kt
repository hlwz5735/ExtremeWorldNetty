package com.duke.protobuf.server.modules.map.model

import com.duke.protobuf.data.MapCharacterEnterResponse
import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.datadefine.MapDefine
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.net.pojo.OnlineUser
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class GameMap(
    internal val define: MapDefine
) {

    /** 当前地图中的角色列表，键为角色的ID */
    private val characterMap = ConcurrentHashMap<Int, CharacterWithSession>()

    val id get() = define.id

    internal fun playerEnter(character: PlayerCharacter, session: NettySession<OnlineUser>) {
        logger.info("playerEnter: Map:{0} characterId:{1}", this.id, character.id)
        character.mapId = this.id

        // 向其他玩家角色发送角色进入的消息
        val responseForOther = MapCharacterEnterResponse.newBuilder()
            .setMapId(id)
            // 只包含当前用户信息
            .addCharacters(character.toNetCharacterInfo())
            .build()
        this.characterMap.values.forEach {
            it.session.channel.writeAndFlush(buildResponseMessage(responseForOther))
        }

        this.characterMap[character.id] = CharacterWithSession(session, character)
        val characterList = this.characterMap.values
            .map { it.character.toNetCharacterInfo() }
            .toList()

        val response = MapCharacterEnterResponse.newBuilder()
            .setMapId(this.id)
            .addAllCharacters(characterList)
            .build()

        session.channel.writeAndFlush(buildResponseMessage(response))
    }

    internal fun update() {

    }

    private fun buildResponseMessage(obj: MapCharacterEnterResponse): NetMessage {
        val builder = NetMessage.newBuilder()
        builder.responseBuilder.mapCharacterEnter = obj
        return builder.build()
    }

    inner class CharacterWithSession (
        val session: NettySession<OnlineUser>,
        val character: PlayerCharacter
    )

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}