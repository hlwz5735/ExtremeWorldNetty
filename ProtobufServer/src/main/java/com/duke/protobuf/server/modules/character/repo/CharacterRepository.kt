package com.duke.protobuf.server.modules.character.repo

import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<TCharacter, Int> {
    fun findByPlayerId(id: Int?): MutableList<TCharacter>
}
