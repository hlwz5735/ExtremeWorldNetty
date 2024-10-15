package com.duke.protobuf.server.modules.map.model

import com.duke.protobuf.data.ProtoMessages.*
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.datadefine.MapDefine
import com.duke.protobuf.server.modules.game.entity.GameEntity
import com.duke.protobuf.server.modules.game.entity.Monster
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.server.modules.game.manager.GameEntityManager
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.map.manager.MonsterManager
import com.duke.protobuf.server.modules.map.manager.SpawnManager
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class GameMap(
    private val define: MapDefine,
    private val gameEntityManager: GameEntityManager,
    dataDefineManager: DataDefineManager,
) {
    /** 当前地图中的角色列表，键为角色的ID */
    private val characterMap = ConcurrentHashMap<Int, CharacterWithSession>()

    val monsterManager = MonsterManager(this, gameEntityManager)
    private val spawnManager = SpawnManager(this, dataDefineManager)

    val id get() = define.id

    fun characterEnterMap(character: PlayerCharacter, session: NettySession<OnlineUser>) {
        // 在正式发出数据前，给实体ID赋值
        this.gameEntityManager.addToMap(mapId = this.id, entity = character)

        logger.info("玩家角色【{}-{}-{}】进入地图 【{}-{}】。", character.id, character.dbId, character.name,
            this.id, this.define.name)
        character.mapId = this.id

        // 向其他玩家角色发送角色进入的消息
        val responseForOther = MapCharacterEnterResponse.newBuilder()
            .setMapId(id)
            // 只包含当前用户信息
            .addCharacters(character.toNetCharacterInfo())

        this.characterMap.values.forEach {
            if (it.character.id != character.id) {
                it.session.sendSync { builder -> builder.setMapCharacterEnter(responseForOther) }
            }
        }

        // 向当前玩家发送目前地图上所有的角色（包括玩家、怪物）信息
        this.characterMap[character.entityId] = CharacterWithSession(session, character)
        val characterList = this.characterMap.values
            .map { it.character.toNetCharacterInfo() }
            .toList()
        val monsterList = this.monsterManager.monsterDic.values
            .map { it.toNetCharacterInfo() }
            .toList()

        val charEnterResponseForPlayer = MapCharacterEnterResponse.newBuilder()
            .setMapId(this.id)
            .addAllCharacters(characterList)
            .addAllCharacters(monsterList)
        session.sendSync { it.setMapCharacterEnter(charEnterResponseForPlayer) }
    }

    fun characterLeaveMap(character: PlayerCharacter) {
        logger.info("玩家角色【{}-{}-{}】离开地图 【{}-{}】。",
            character.id, character.dbId, character.name,
            this.id, this.define.name)
        this.gameEntityManager.removeFromMap(this.id, character)

        character.mapId = null

        val response = MapCharacterLeaveResponse.newBuilder()
            .setEntityId(character.id)

        this.characterMap.values.forEach {
            it.session.sendSync { builder -> builder.setMapCharacterLeave(response) }
        }
        this.characterMap.remove(character.entityId)
    }

    fun monsterEnter(monster: Monster) {
        logger.info("怪物【{}-{}】进入地图【{}-{}】。", monster.id, monster.name, this.id, this.define.name)
        // 向所有玩家角色发送怪物进入的消息
        val response = MapCharacterEnterResponse.newBuilder()
            .setMapId(id)
            // 只包含当前怪物的信息
            .addCharacters(monster.toNetCharacterInfo())
            .build()
        this.characterMap.values.forEach {
            it.session.sendSync { builder -> builder.setMapCharacterEnter(response) }
        }
    }

    fun update() {
        spawnManager.update()
    }

    /**
     * 更新地图内的实体信息
     */
    fun updateEntity(gameEntity: GameEntity, entityEvent: NEntitySync.ENTITY_EVENT) {
        val entityId = gameEntity.id

        val responseForOther = MapEntitySyncResponse.newBuilder()
            .addEntitySyncs(
                NEntitySync.newBuilder()
                    .setId(gameEntity.id)
                    .setEvent(entityEvent)
                    .setEntity(gameEntity.toNetEntity()))

        this.characterMap.values.forEach {
            val character = it.character
            if (character.id == entityId) {
                character.position = gameEntity.position
                character.direction = gameEntity.direction
                character.speed = gameEntity.speed
            } else {
                it.session.sendSync { builder -> builder.setMapEntitySync(responseForOther) }
            }
        }
    }

    inner class CharacterWithSession (
        val session: NettySession<OnlineUser>,
        val character: PlayerCharacter
    )

    companion object {
        private val logger = LoggerFactory.getLogger(GameMap::class.java)
    }
}
