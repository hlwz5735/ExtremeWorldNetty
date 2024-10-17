package com.duke.protobuf.server.modules.guild.repo

import com.duke.protobuf.data.ProtoMessages.NGuildApplyInfo.APPLY_RESULT
import com.duke.protobuf.server.modules.guild.dbentity.TGuildApply
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildApplyRepository : JpaRepository<TGuildApply, Int> {
    fun queryByOwnerId(ownerId: Int): List<TGuildApply>
    fun queryByOwnerIdAndResult(ownerId: Int, result: APPLY_RESULT): List<TGuildApply>

    fun getByOwnerIdAndCharacterId(ownerId: Int, memberId: Int): TGuildApply?
}
