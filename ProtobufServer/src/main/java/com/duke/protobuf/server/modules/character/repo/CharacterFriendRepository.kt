package com.duke.protobuf.server.modules.character.repo

import com.duke.protobuf.server.modules.character.dbentity.TCharacterFriend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CharacterFriendRepository : JpaRepository<TCharacterFriend, Int> {
    @Query("from TCharacterFriend where owner.id = :charId and friendId = :friendCharId")
    fun getFriendByCharacterId(charId: Int, friendCharId: Int): TCharacterFriend?

    fun queryByOwnerId(ownerId: Int): List<TCharacterFriend>
}
