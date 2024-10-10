package com.duke.protobuf.server.modules.user

import com.duke.protobuf.server.modules.game.net.OnlineUser
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class OnlineUserManager {
    /** 用户ID - 在线用户的列表 */
    private val onlineUsers: MutableMap<Int, OnlineUser> = ConcurrentHashMap()

    fun clear() {
        this.onlineUsers.clear()
    }

    operator fun get(id: Int): OnlineUser? {
        return this.onlineUsers[id]
    }

    fun add(user: OnlineUser) {
        this.onlineUsers[user.id] = user
    }

    fun removeById(id: Int) {
        this.onlineUsers.remove(id)
    }

    fun getByCharacterId(characterId: Int): OnlineUser? {
        for (onlineUser in this.onlineUsers.values) {
            if (onlineUser.character?.dbId == characterId) {
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
