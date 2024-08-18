package com.duke.protobuf.server.modules.item.service

import com.duke.protobuf.server.modules.item.dbentity.TCharacterItem
import com.duke.protobuf.server.modules.item.repo.CharacterItemRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ItemService(val repository: CharacterItemRepository) {
    @Transactional
    fun save(item: TCharacterItem) {
        // TODO: 这里是同步的保存，未来考虑改为异步
        repository.save(item)
    }
}
