package com.duke.protobuf.server.modules.character.manager

import com.duke.protobuf.data.ProtoMessages.NStatus
import com.duke.protobuf.data.ProtoMessages.StatusNotify
import com.duke.protobuf.server.modules.character.service.ItemService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter

/**
 * 角色状态管理器
 */
class StatusManager(
    private val owner: PlayerCharacter,
    private val itemService: ItemService,
) {
    private val statusList: MutableList<NStatus> = arrayListOf()

    fun hasStatus(): Boolean {
        return statusList.isNotEmpty()
    }

    /**
     * 增加状态变动
     */
    fun addStatus(type: NStatus.STATUS_TYPE, id: Int, amount: Int, action: NStatus.STATUS_ACTION) {
        this.statusList.add(NStatus.newBuilder()
            .setType(type)
            .setId(id)
            .setValue(amount)
            .setAction(action)
            .build())
    }

    fun modifyMoney(amount: Int) {
        if (amount == 0) {
            return
        }
        if (amount > 0) {
            addStatus(NStatus.STATUS_TYPE.MONEY, 0, amount, NStatus.STATUS_ACTION.ADD)
        } else {
            addStatus(NStatus.STATUS_TYPE.MONEY, 0, -amount, NStatus.STATUS_ACTION.DELETE)
        }
    }

    fun modifyItem(id: Int, count: Int, action: NStatus.STATUS_ACTION) {
        addStatus(NStatus.STATUS_TYPE.ITEM, id, count, action)
    }

    fun collectChangesToResponse(): StatusNotify? {
        if (statusList.isEmpty()) {
            return null
        }

        val statusResponse = StatusNotify.newBuilder()
        statusList.forEach {
            statusResponse.addStatus(it)
        }
        val res = statusResponse.build()

        // 清空已经收集的变更
        this.statusList.clear()
        return res
    }
}
