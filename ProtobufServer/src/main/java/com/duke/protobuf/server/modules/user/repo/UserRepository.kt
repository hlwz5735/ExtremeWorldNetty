package com.duke.protobuf.server.modules.user.repo

import com.duke.protobuf.server.modules.user.dbentity.TUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<TUser, Int> {
    fun getByUsername(username: String): TUser?
}
