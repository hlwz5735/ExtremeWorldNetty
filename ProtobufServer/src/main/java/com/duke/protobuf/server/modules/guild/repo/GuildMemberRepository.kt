package com.duke.protobuf.server.modules.guild.repo

import com.duke.protobuf.server.modules.guild.dbentity.TGuildMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildMemberRepository : JpaRepository<TGuildMember, Int> {
    fun queryByOwnerId(ownerId: Int): List<TGuildMember>
    fun getByCharacterId(characterId: Int): TGuildMember?
    fun countByOwnerId(ownerId: Int): Long
}
