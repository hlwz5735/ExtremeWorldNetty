package com.duke.protobuf.server.modules.item.model

import com.duke.protobuf.server.modules.item.dbentity.TCharacterItem

/**
 * 道具信息的内存版本，避免频繁的数据库操作
 */
class Item(val dbItem: TCharacterItem) {
    var itemId: Int = dbItem.id!!
    var count: Int = dbItem.itemCount ?: 0

    fun increaseCount(count: Int) {
        this.count += count
        dbItem.itemCount = this.count
    }

    fun decreaseCount(count: Int) {
        this.count -= count
        dbItem.itemCount = this.count
    }

    /**
     * 使用道具
     */
    fun use(count: Int = 1): Boolean {
        // TODO: 尚未实现
        return false
    }

    override fun toString(): String {
        return "Item(id=$itemId, count=$count)"
    }
}
