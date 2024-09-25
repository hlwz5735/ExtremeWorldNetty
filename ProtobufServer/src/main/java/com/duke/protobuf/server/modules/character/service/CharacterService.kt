package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import com.duke.protobuf.server.modules.character.dbentity.TCharacterBag
import com.duke.protobuf.server.modules.character.repo.CharacterBagRepository
import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import org.hibernate.Hibernate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterService(
    private val repo: CharacterRepository,
    private val bagRepo: CharacterBagRepository,
    private val bagService: BagService,
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
            player = player,
            carriedMoney = 100000
        )
        repo.save(character)

        bagService.createBagForCharacter(character.id!!)

        val characters = repo.findByPlayerId(player.id)
        player.characters = characters
    }

    @Transactional
    fun getById(id: Int): TCharacter? {
        return repo.findById(id).map {
            Hibernate.initialize(it.items)
            Hibernate.initialize(it.equips)
            it
        }.orElseGet(null)
    }
}
