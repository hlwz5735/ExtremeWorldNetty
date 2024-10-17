package com.duke.protobuf.server.modules.guild.repo

import com.duke.protobuf.server.modules.guild.dbentity.TGuild
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildRepository : JpaRepository<TGuild, Int> {
    fun countByName(name: String): Long
}
