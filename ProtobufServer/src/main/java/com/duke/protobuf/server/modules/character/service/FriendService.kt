package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.data.NFriendInfo
import com.duke.protobuf.server.modules.character.dbentity.TCharacterFriend
import com.duke.protobuf.server.modules.character.repo.CharacterFriendRepository
import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import com.duke.protobuf.server.modules.user.OnlineUserManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendService(
    val repo: CharacterFriendRepository,
    val characterRepo: CharacterRepository,
    val onlineUserManager: OnlineUserManager,
) {
    @Transactional
    fun createFriendRelationship(char1Id: Int, char2Id: Int) {
        val char1o = characterRepo.findById(char1Id)
        val char2o = characterRepo.findById(char2Id)
        if (char1o.isEmpty || char2o.isEmpty) {
            throw RuntimeException("请求的角色居然不存在！")
        }

        val char1 = char1o.get()
        val char2 = char2o.get()
        var entity = TCharacterFriend(
            friendId = char2Id,
            friendName = char2.name!!,
            clazz = char2.clazz,
            level = char2.level,
            owner = char1
        )
        repo.save(entity)
        entity = TCharacterFriend(
            friendId = char1Id,
            friendName = char1.name!!,
            clazz = char1.clazz,
            level = char1.level,
            owner = char2
        )
        repo.save(entity)
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

    @Transactional(readOnly = true)
    fun getFriendByCharacterId(charId: Int, friendCharId: Int): TCharacterFriend? {
        return repo.getFriendByCharacterId(charId, friendCharId)
    }

    @Transactional(readOnly = true)
    fun listFriendsByCharacterId(charId: Int): List<NFriendInfo> {
        val entityList = repo.queryByOwnerId(charId)
        return entityList
            .mapNotNull(::convertEntityToNetData)
            .toList()
    }

    private fun convertEntityToNetData(entity: TCharacterFriend): NFriendInfo? {
        val onlineUser = onlineUserManager.getByCharacterId(entity.friendId!!)
        if (onlineUser == null) {
            return NFriendInfo.newBuilder()
                .setId(entity.id!!)
                .setFriendInfo(NCharacterInfo.newBuilder()
                    .setId(entity.friendId!!)
                    .setName(entity.friendName)
                    .setLevel(entity.level)
                    .setClass_(entity.clazz)
                )
                .setStatus(0)
                .build()
        } else {
            return NFriendInfo.newBuilder()
                .setId(entity.id!!)
                .setFriendInfo(NCharacterInfo.newBuilder()
                    .setId(entity.friendId!!)
                    .setName(entity.friendName)
                    .setLevel(onlineUser.character!!.dbId)
                    .setClass_(entity.clazz)
                )
                .setStatus(1)
                .build()
        }
    }
}
