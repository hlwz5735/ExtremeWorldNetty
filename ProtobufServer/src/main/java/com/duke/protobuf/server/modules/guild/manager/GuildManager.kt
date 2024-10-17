package com.duke.protobuf.server.modules.guild.manager

import com.duke.protobuf.server.modules.guild.model.Guild
import com.duke.protobuf.server.modules.guild.service.GuildService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class GuildManager(
    private val service: GuildService,
) {
    private val guildMap = ConcurrentHashMap<Int, Guild>()

    operator fun get(guildId: Int): Guild? {
        if (!guildMap.containsKey(guildId)) {
            val tGuild = service.getById(guildId) ?: return null
            guildMap[guildId] = Guild(tGuild)
        }
        return guildMap[guildId]
    }

    fun getCharacterGuild(charId: Int): Guild? {
        val tGuild = service.getCharacterGuild(charId) ?: return null
        return this[tGuild.id!!]
    }
}
