package com.duke.protobuf.server.modules.character.manager

import com.duke.protobuf.data.ProtoMessages.NItemInfo
import com.duke.protobuf.data.ProtoMessages.NStatus
import com.duke.protobuf.server.modules.character.dbentity.TCharacterItem
import com.duke.protobuf.server.modules.character.model.Item
import com.duke.protobuf.server.modules.character.service.ItemService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.util.SpringContextUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class ItemManager(
    private val owner: PlayerCharacter,
) {
    private val itemService = SpringContextUtil.getBean(ItemService::class.java)!!
    private val itemDic: MutableMap<Int, Item> = ConcurrentHashMap()

    init {
        for (tCharacterItem in owner.tableData.items) {
            itemDic[tCharacterItem.itemId] = Item(tCharacterItem)
        }
    }

    fun useItem(itemId: Int, count: Int = 1): Boolean {
        if (count == 0) {
            return false
        }
        logger.info("[{}] UseItem [{}:{}]", owner.tableData.id, itemId, count)
        val item = this.itemDic[itemId]
        if (item != null) {
            if (item.count < count) {
                return false
            }
            // TODO: 增加使用逻辑

            // 减去用掉的道具数量
            item.decreaseCount(count)
            return true
        }
        return false
    }

    fun hasItem(itemId: Int): Boolean {
        val item = this.itemDic[itemId]
        return (item?.count ?: 0) > 0
    }

    fun getItem(itemId: Int): Item? {
        val item = this.itemDic[itemId]
        if (item != null) {
            logger.info("[{}] GetItem [{}:{}]", owner.tableData.id, itemId, item)
        }
        return item
    }

    /**
     * 给当前角色发放道具
     */
    fun giveItem(itemId: Int, count: Int): Boolean {
        if (count == 0) {
            return true
        }
        var item = this.itemDic[itemId]
        if (item != null) {
            item.increaseCount(count)
            itemService.save(item.dbItem)
        } else {
            // 创建并插入记录
            val dbItem = TCharacterItem(
                owner = owner.tableData,
                itemId = itemId,
                itemCount = count,
            )
            itemService.save(dbItem)
            item = Item(dbItem)
            this.itemDic[itemId] = item
        }
        owner.statusManager.modifyItem(itemId, count, NStatus.STATUS_ACTION.ADD)
        return true
    }

    /**
     * 核销当前角色的指定道具
     */
    fun destroyItem(itemId: Int, count: Int): Boolean {
        if (count == 0) {
            return true
        }
        val item = this.itemDic[itemId]
        if (item == null) {
            return false
        }
        if (item.count < count) {
            return false
        }
        item.decreaseCount(count)
        logger.info("[{}] DestroyItem [{}:{}]", owner.tableData.id, itemId, count)
        itemService.save(item.dbItem)
        owner.statusManager.modifyItem(itemId, count, NStatus.STATUS_ACTION.DELETE)
        return true
    }

    /**
     * 将当前角色的道具信息构建为网络协议对象
     */
    fun buildItemInfos(): List<NItemInfo> {
        val list = ArrayList<NItemInfo>(itemDic.size)
        for (entry in itemDic.entries) {
            val item = entry.value
            list.add(NItemInfo.newBuilder()
                .setId(item.itemId)
                .setCount(item.count)
                .build()
            )
        }
        return list
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ItemManager::class.java)
    }
}
