package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import com.duke.protobuf.server.modules.character.dbentity.TCharacterBag
import com.duke.protobuf.server.modules.character.repo.CharacterBagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BagService(
    val repo: CharacterBagRepository
) {
    @Transactional
    fun getByCharacterId(characterId: Int): TCharacterBag {
        return repo.getByOwnerId(characterId)
            ?: createBagForCharacter(characterId)
    }

    @Transactional
    fun save(tBag: TCharacterBag): TCharacterBag {
        return repo.save(tBag)
    }

    /**
     * 为指定的角色创建背包信息
     */
    internal fun createBagForCharacter(characterId: Int): TCharacterBag {
        val bag = TCharacterBag(
            owner = TCharacter(characterId),
            unlockedCellCount = 20,
            items = byteArrayOf()
        )
        return repo.save(bag)
    }
}
