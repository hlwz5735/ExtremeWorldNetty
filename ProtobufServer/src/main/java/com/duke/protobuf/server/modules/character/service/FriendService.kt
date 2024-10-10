package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.data.NFriendInfo
import com.duke.protobuf.server.modules.character.dbentity.TCharacterFriend
import com.duke.protobuf.server.modules.character.manager.FriendInfo
import com.duke.protobuf.server.modules.character.repo.CharacterFriendRepository
import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import com.duke.protobuf.structure.DTuple
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.Tuple

@Service
class FriendService(
    val repo: CharacterFriendRepository,
    val characterRepo: CharacterRepository,
    val onlineCharacterManager: OnlineCharacterManager,
) {
    @Transactional
    fun createFriendRelationship(char1Id: Int, char2Id: Int): DTuple<TCharacterFriend, TCharacterFriend> {
        val char1o = characterRepo.findById(char1Id)
        val char2o = characterRepo.findById(char2Id)
        if (char1o.isEmpty || char2o.isEmpty) {
            throw RuntimeException("请求的角色居然不存在！")
        }

        val char1 = char1o.get()
        val char2 = char2o.get()
        val entity1 = TCharacterFriend(
            friendId = char2Id,
            friendName = char2.name!!,
            clazz = char2.clazz,
            level = char2.level,
            owner = char1
        )
        repo.save(entity1)
        val entity2 = TCharacterFriend(
            friendId = char1Id,
            friendName = char1.name!!,
            clazz = char1.clazz,
            level = char1.level,
            owner = char2
        )
        repo.save(entity2)
        return DTuple(entity1, entity2)
    }

    @Transactional
    fun removeRelationship(charId: Int, friendId: Int) {
        val record1 = repo.getFriendByCharacterId(charId, friendId)
        if (record1 != null) {
            repo.delete(record1)
        }
        val record2 = repo.getFriendByCharacterId(friendId, charId)
        if (record2 != null) {
            repo.delete(record2)
        }
    }

    @Transactional
    fun updateByCharInfo(ownerId: Int, friendId: Int, info: NCharacterInfo) {
        val entity = repo.getFriendByCharacterId(ownerId, friendId) ?: return
        entity.level = info.level
        entity.friendName = info.name
        repo.save(entity)
    }

    @Transactional(readOnly = true)
    fun getFriendByCharacterId(charId: Int, friendCharId: Int): TCharacterFriend? {
        return repo.getFriendByCharacterId(charId, friendCharId)
    }

    @Transactional(readOnly = true)
    fun listFriendsByCharacterId(charId: Int): List<FriendInfo> {
        val entityList = repo.queryByOwnerId(charId)
        return entityList
            .map(::convertEntityToModel)
            .toList()
    }

    private fun convertEntityToModel(entity: TCharacterFriend): FriendInfo {
        val onlineUser = onlineCharacterManager[entity.friendId!!]
        return FriendInfo(
            entity.id!!,
            entity.owner!!.id!!,
            entity.friendId!!,
            entity.friendName,
            onlineUser?.character?.level ?: entity.level,
            entity.clazz,
            if (onlineUser == null) 0 else 1
        )
    }
}
