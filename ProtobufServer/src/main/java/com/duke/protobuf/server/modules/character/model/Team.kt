package com.duke.protobuf.server.modules.character.model

import com.duke.protobuf.data.NTeamInfo
import com.duke.protobuf.data.NetMessageResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.data.TeamInfoResponse
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.net.OnlineUser
import java.util.concurrent.atomic.AtomicInteger

class Team(
    leader: PlayerCharacter,
) {
    val id: Int
    var leader: PlayerCharacter?
    val members: MutableList<PlayerCharacter> = mutableListOf()
    var timestamp: Long = 0

    init {
        this.leader = leader
        id = idGenerator.incrementAndGet()
        addMember(leader)
    }

    fun addMember(member: PlayerCharacter) {
        if (members.isEmpty()) {
            leader = member
        }
        members.add(member)
        member.team = this
        timestamp = System.currentTimeMillis()
    }

    fun memberLeave(member: PlayerCharacter) {
        members.remove(member)
        if (member == leader) {
            if (members.isNotEmpty()) {
                leader = members[0]
            } else {
                leader = null
            }
        }
        member.team = null
        timestamp = System.currentTimeMillis()
    }

    fun postProcess(session: NettySession<OnlineUser>) {
        val leader = this.leader ?: return
        session.sendAsync {
            it.setTeamInfo(TeamInfoResponse.newBuilder()
                .setResult(RESULT.SUCCESS)
                .setTeamInfo(NTeamInfo.newBuilder()
                    .setId(id)
                    .setLeaderId(leader.dbId)
                    .addAllMembers(members.map { it.getBasicInfo() })
            ))
        }
    }

    companion object {
        /** 序号生成器 */
        private val idGenerator = AtomicInteger()
    }
}
