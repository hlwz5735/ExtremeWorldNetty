package com.duke.protobuf.server.modules.user.repo

import com.duke.protobuf.server.modules.user.dbentity.TPlayer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<TPlayer, Int> {
}