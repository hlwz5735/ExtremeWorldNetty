package com.duke.protobuf.server.modules.user.repo

import com.duke.protobuf.server.modules.user.dbentity.TCharacter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<TCharacter, Int> {
}