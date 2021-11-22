package com.duke.protobuf.server.modules.game

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
}
