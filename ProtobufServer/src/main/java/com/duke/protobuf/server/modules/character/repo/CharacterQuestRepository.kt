package com.duke.protobuf.server.modules.character.repo

import com.duke.protobuf.server.modules.character.dbentity.TCharacterQuest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterQuestRepository : JpaRepository<TCharacterQuest, Int> {
    fun findByOwnerId(ownerId: Int): List<TCharacterQuest>

    fun getByOwnerIdAndQuestId(ownerId: Int, questId: Int): TCharacterQuest
}
