package com.duke.protobuf.server.modules.character.manager

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.data.NCharacterInfo
import com.duke.protobuf.data.NFriendInfo
import com.duke.protobuf.server.modules.character.service.FriendService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import com.duke.protobuf.server.util.SpringContextUtil

data class FriendInfo(
    val dbId: Int,
    val ownerId: Int,
    val friendId: Int,
    var friendName: String,
    var friendLevel: Int,
    val friendClass: CHARACTER_CLASS,
    var onlineStatus: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FriendInfo

        return dbId == other.dbId
    }

    override fun hashCode(): Int {
        return dbId
    }
}

class FriendManager(
    val owner: PlayerCharacter,
    private val onlineCharacterManager: OnlineCharacterManager,
) {
    private val service = SpringContextUtil.getBean(FriendService::class.java)!!
    private val friendList: MutableList<FriendInfo> = mutableListOf()
    var isDirty = false

    init {
        friendList.addAll(service.listFriendsByCharacterId(owner.dbId))
    }

    val nFriendList get() = friendList.map {
        NFriendInfo.newBuilder()
            .setId(it.dbId)
            .setStatus(it.onlineStatus)
            .setFriendInfo(NCharacterInfo.newBuilder()
                .setId(it.friendId)
                .setName(it.friendName)
                .setLevel(it.friendLevel)
                .setClass_(it.friendClass)
            )
            .build()
    }.toList()

    fun addFriend(friendId: Int) {
        // 更新数据库
        val entityTuple = service.createFriendRelationship(owner.dbId, friendId)

        // 更新内存数据
        var entity = entityTuple.first!!
        friendList.add(FriendInfo(
            entity.id!!,
            owner.dbId,
            friendId,
            entity.friendName,
            entity.level,
            entity.clazz,
            1,
        ))
        isDirty = true
        val friend = onlineCharacterManager[friendId]?.character ?: return
        entity = entityTuple.second!!
        friend.friendManager.friendList.add(FriendInfo(
            entity.id!!,
            friendId,
            owner.dbId,
            entity.friendName,
            entity.level,
            entity.clazz,
            1,
        ))
        friend.friendManager.isDirty = true
    }

    fun removeFriend(friendId: Int) {
        // 更新数据库
        service.removeRelationship(owner.dbId, friendId)
        // 更新内存数据
        val info = getFriend(friendId)
        if (info != null) {
            friendList.remove(info)
            isDirty = true
        }
        val friend = onlineCharacterManager[friendId]?.character ?: return
        val myInfo = friend.friendManager.getFriend(owner.dbId) ?: return
        friend.friendManager.friendList.remove(myInfo)
        friend.friendManager.isDirty = true
    }

    fun statusUpdate(friendId: Int, onlineStatus: Int) {
        val rel = getFriend(friendId) ?: return
        rel.onlineStatus = if (onlineStatus == 0) 0 else 1
        isDirty = true
    }

    fun infoUpdate(friendId: Int, info: NCharacterInfo) {
        service.updateByCharInfo(owner.dbId, friendId, info)
        val rel = getFriend(friendId) ?: return
        rel.friendName = info.name
        rel.friendLevel = info.level
        isDirty = true
    }

    fun getFriend(friendId: Int): FriendInfo? {
        return friendList.find { it.friendId == friendId }
    }

    fun tellFriendOnline() {
        friendList.forEach {
            val friend = onlineCharacterManager[it.friendId]?.character ?: return@forEach
            friend.friendManager.statusUpdate(owner.dbId, 1)
        }
    }

    fun tellFriendOffline() {
        friendList.forEach {
            val friend = onlineCharacterManager[it.friendId]?.character ?: return@forEach
            friend.friendManager.statusUpdate(owner.dbId, 0)
        }
    }
}