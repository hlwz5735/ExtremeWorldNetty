package com.duke.protobuf.server.net.pojo

import com.duke.protobuf.netty.SessionUser
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.user.dbentity.TUser

class OnlineUser(
    /** 当前玩家的数据库数据 */
    val tableData: TUser,
    /** 当前玩家角色，如果未进入游戏则为空 */
    var character: PlayerCharacter? = null,
): SessionUser {
    override val id get() = tableData.id!!
}
