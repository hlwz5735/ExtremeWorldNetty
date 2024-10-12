package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.server.modules.character.model.Team
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import org.springframework.stereotype.Service

@Service
class TeamService {
    private val teamList = mutableListOf<Team>()
/*    private val charTeamDic = mutableMapOf<Int, Team>()

    fun getTeamByCharacterId(characterId: Int): Team? {
        return charTeamDic[characterId]
    }*/

    fun addTeamMember(leader: PlayerCharacter, member: PlayerCharacter) {
        if (leader.team == null) {
            leader.team = createTeamForLeader(leader)
        }
        leader.team!!.addMember(member)
    }

    private fun createTeamForLeader(leader: PlayerCharacter): Team {
        // 首先遍历复用已有的组队实例
        for (team in teamList) {
            if (team.members.isEmpty()) {
                team.addMember(leader)
                return team
            }
        }
        // 没遍历到，创建新队伍实例
        val team = Team(leader)
        teamList.add(team)
        return team
    }
}
