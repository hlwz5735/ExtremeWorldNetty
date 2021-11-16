package com.duke.protobuf.server.modules.user.service

import com.duke.protobuf.server.modules.user.dbentity.TUser
import com.duke.protobuf.server.modules.user.repo.UserRepository
import org.springframework.stereotype.Service

typealias User = TUser

@Service
class UserService(private val repo: UserRepository) {
    fun checkLogin(username: String, password: String): Boolean {
        val user = repo.getByUsername(username)
        if (user == null) {
            return false
        } else {
            return user.password == password
        }
    }
}
