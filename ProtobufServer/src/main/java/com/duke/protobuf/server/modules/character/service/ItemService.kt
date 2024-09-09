package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.server.modules.character.dbentity.TCharacterItem
import com.duke.protobuf.server.modules.character.repo.CharacterItemRepository
import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.structure.DTuple
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ItemService(
    val repository: CharacterItemRepository,
    val dataDefineManager: DataDefineManager,
    val characterRepository: CharacterRepository,
) {
    @Transactional
    fun save(item: TCharacterItem) {
        // TODO: 这里是同步的保存，未来考虑改为异步
        repository.save(item)
    }

    @Transactional
    fun buyItem(character: PlayerCharacter, shopId: Int, shopItemId: Int): DTuple<Boolean, String> {
        logger.info("用户购买请求：角色{}, 商店{}，道具{}", character.id, shopId, shopItemId)

        val shop = dataDefineManager.shopDic[shopId]
            ?: return DTuple(false, "指定的商店不存在！")
        val item = dataDefineManager.shopItemDic[shopId]?.get(shopItemId)
            ?: return DTuple(false, "指定的商品不存在！")
        logger.info("执行购买，角色{}, 商店{}，道具{}，数量{}, 总价{}",
            character.id, shopId, shopItemId, item.count, item.price)
        if (character.tableData.carriedMoney > item.price) {
            character.itemManager.giveItem(item.itemId, item.count)
            character.tableData.carriedMoney -= item.price
            // 通知客户端和写库这里是分别做的
            character.statusManager.modifyMoney(-item.price)
            characterRepository.save(character.tableData)
            return DTuple(true)
        } else {
            return DTuple(false, "您带的钱不够多呀。")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ItemService::class.java.name)
    }
}
