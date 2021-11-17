package com.duke.protobuf.server.modules.user.service

import com.duke.protobuf.server.modules.user.dbentity.TPlayer
import com.duke.protobuf.server.modules.user.dbentity.TUser
import com.duke.protobuf.server.modules.user.repo.UserRepository
import com.duke.protobuf.structure.DTuple
import org.springframework.stereotype.Service
import java.time.LocalDateTime

typealias User = TUser

@Service
class UserService(private val repo: UserRepository) {
    /**
     * 处理用户登录校验
     */
    fun checkLogin(username: String, password: String): DTuple<Boolean, String?> {
        val user = repo.getByUsername(username)
        return if (user == null) {
            DTuple(false, "用户不存在")
        } else {
            DTuple(user.password == password)
        }
    }

    /**
     * 处理用户注册
     */
    fun registerUser(username: String, password: String): DTuple<Boolean, String?> {
        val user = repo.getByUsername(username)
        if (user != null) {
            return DTuple(false, "用户名已存在。")
        }

        val newUser = User(
            username = username,
            password = password,
            registerTime = LocalDateTime.now(),
            player = TPlayer()
        )

        repo.save(newUser)
        return DTuple(true)
    }
}
