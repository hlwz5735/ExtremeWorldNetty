package com.duke.protobuf.server.modules.character.repo

import com.duke.protobuf.server.modules.character.dbentity.TCharacterItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterItemRepository : JpaRepository<TCharacterItem, Int> {
}
