package com.duke.protobuf.server.modules.user.service

import com.duke.protobuf.server.modules.user.dbentity.TUser
import com.duke.protobuf.server.modules.user.repo.UserRepository
import com.duke.protobuf.structure.DTuple
import org.springframework.stereotype.Service

typealias User = TUser

@Service
class UserService(private val repo: UserRepository) {
    fun checkLogin(username: String, password: String): DTuple<Boolean, String?> {
        val user = repo.getByUsername(username)
        return if (user == null) {
            DTuple(false, "用户不存在")
        } else {
            DTuple(user.password == password)
        }
    }
}
