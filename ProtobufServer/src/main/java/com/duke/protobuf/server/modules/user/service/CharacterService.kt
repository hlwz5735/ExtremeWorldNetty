package com.duke.protobuf.server.modules.user.service

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.user.dbentity.TCharacter
import com.duke.protobuf.server.modules.user.repo.CharacterRepository
import com.duke.protobuf.server.modules.game.net.OnlineUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterService(
    private val repo: CharacterRepository
) {
    @Transactional
    fun createCharacter(session: NettySession<OnlineUser>, name: String, clazz: CHARACTER_CLASS) {
        val player = session.user.tableData.player!!

        val character = TCharacter(
            tid = clazz.ordinal,
            clazz = clazz,
            name = name,
            mapId = 1,
            mapPosX = 5000,
            mapPosY = 4000,
            mapPosZ = 820,
            player = player
        )

        repo.save(character)

        val characters = repo.findByPlayerId(player.id)
        player.characters = characters
    }
}