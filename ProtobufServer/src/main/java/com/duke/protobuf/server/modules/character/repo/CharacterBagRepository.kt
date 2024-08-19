package com.duke.protobuf.server.modules.character.repo

import com.duke.protobuf.server.modules.character.dbentity.TCharacterBag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CharacterBagRepository : JpaRepository<TCharacterBag, Int> {
    @Query("from TCharacterBag where owner.id = :ownerId")
    fun getByOwnerId(ownerId: Int): TCharacterBag?
}
