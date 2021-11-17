package com.duke.protobuf.server.modules.game

import com.duke.protobuf.server.modules.game.datadefine.CharacterDefine
import com.duke.protobuf.server.modules.game.datadefine.MapDefine
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
    var gameMapDic: Map<Int, MapDefine> = emptyMap()
    var characterDic: Map<Int, CharacterDefine> = emptyMap()

    @PostConstruct
    fun init() {
        if (mapDefineFile != null) {
            this.gameMapDic = objectMapper.readValue(File(mapDefineFile))
        }
    }

    companion object {
        private val mapDefineFile = this::class.java.classLoader.getResource("data/MapDefine.txt")?.file
    }
}