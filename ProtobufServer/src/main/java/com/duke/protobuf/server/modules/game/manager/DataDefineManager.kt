package com.duke.protobuf.server.modules.game.manager

import com.duke.protobuf.server.modules.game.datadefine.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import javax.annotation.PostConstruct

/**
 * 预定义数据管理器
 */
@Component
class DataDefineManager(
    private val objectMapper: ObjectMapper
) {
    var characterDic: Map<Int, CharacterDefine> = emptyMap()
    var gameMapDic: Map<Int, MapDefine> = emptyMap()
    var teleporterDic: Map<Int, TeleporterDefine> = emptyMap()
    var npcDic: Map<Int, NpcDefine> = emptyMap()
    var itemDic: Map<Int, ItemDefine> = emptyMap()

    @PostConstruct
    fun init() {
        if (characterDefineFile != null) {
            this.characterDic = objectMapper.readValue(File(characterDefineFile))
        }
        logger.info("加载角色配置文件成功，配置数：{}", characterDic.size)
        if (mapDefineFile != null) {
            this.gameMapDic = objectMapper.readValue(File(mapDefineFile))
        }
        logger.info("加载地图配置文件成功，配置数：{}", gameMapDic.size)
        if (teleporterDefineFile != null) {
            this.teleporterDic = objectMapper.readValue(File(teleporterDefineFile))
        }
        logger.info("加载传送点配置文件成功，配置数：{}", teleporterDic.size)
        if (npcDefineFile != null) {
            this.npcDic = objectMapper.readValue(File(npcDefineFile))
        }
        logger.info("加载NPC配置文件成功，配置数：{}", npcDic.size)
        if (itemDefineFile != null) {
            this.itemDic = objectMapper.readValue(File(itemDefineFile))
        }
        logger.info("加载物品配置文件成功，配置数：{}", itemDic.size)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DataDefineManager::class.java)

        private val characterDefineFile = this::class.java.classLoader.getResource("data/CharacterDefine.txt")?.file
        private val mapDefineFile = this::class.java.classLoader.getResource("data/MapDefine.txt")?.file
        private val teleporterDefineFile = this::class.java.classLoader.getResource("data/TeleporterDefine.txt")?.file
        private val npcDefineFile = this::class.java.classLoader.getResource("data/NpcDefine.txt")?.file
        private val itemDefineFile = this::class.java.classLoader.getResource("data/ItemDefine.txt")?.file
    }
}
