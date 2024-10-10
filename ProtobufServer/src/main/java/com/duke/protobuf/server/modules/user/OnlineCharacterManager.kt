package com.duke.protobuf.server.modules.user

import com.duke.protobuf.server.modules.game.net.OnlineUser
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class OnlineCharacterManager {
    /** 角色ID - 在线用户的列表 */
    private val onlineUsers: MutableMap<Int, OnlineUser> = ConcurrentHashMap()

    fun clear() {
        this.onlineUsers.clear()
    }

    operator fun get(characterId: Int): OnlineUser? {
        return this.onlineUsers[characterId]
    }

    fun forEach(func: (OnlineUser) -> Unit) {
        this.onlineUsers.values.forEach(func)
    }

    fun add(user: OnlineUser) {
        this.onlineUsers[user.character!!.dbId] = user
    }

    fun removeByCharacterId(characterId: Int): OnlineUser? {
        return this.onlineUsers.remove(characterId)
    }

    fun getByUserId(userId: Int): OnlineUser? {
        for (onlineUser in this.onlineUsers.values) {
            if (onlineUser.id == userId) {
                return onlineUser
            }
        }
        return null
    }

    fun getByName(name: String): OnlineUser? {
        for (onlineUser in this.onlineUsers.values) {
            if (onlineUser.character?.name == name) {
                return onlineUser
            }
        }
        return null
    }
}
