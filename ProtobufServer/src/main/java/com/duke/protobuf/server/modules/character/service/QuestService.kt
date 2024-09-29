package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.server.modules.character.dbentity.TCharacterQuest
import com.duke.protobuf.server.modules.character.repo.CharacterQuestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestService(
    private val repo: CharacterQuestRepository
) {
    @Transactional
    fun save(quest: TCharacterQuest): TCharacterQuest {
        return repo.save(quest)
    }

    @Transactional(readOnly = true)
    fun listByCharacterId(characterId: Int): List<TCharacterQuest> {
        return repo.findByOwnerId(characterId)
    }

    @Transactional(readOnly = true)
    fun getByCharacterAndDefineId(characterId: Int, defineId: Int): TCharacterQuest? {
        return repo.getByOwnerIdAndQuestId(characterId, defineId)
    }
}
