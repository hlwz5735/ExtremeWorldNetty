package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.data.ITEM_TYPE

enum class ItemFunction {
    RecoverHp,
    RecoverMp,
    AddBuff,
    AddExp,
    AddMoney,
    AddItem,
    AddSkillPoint
}

data class ItemDefine(
    val id: Int,
    val name: String,
    val description: String?,
    val category: String,
    val type: ITEM_TYPE,
    val canUse: Boolean,
    val useCd: Float,
    val price: Int?,
    val sellPrice: Int,
    val stackLimit: Int?,
    val icon: String?,
    val function: ItemFunction?,
    val param: Int?,
    val paramArr: List<Int>?
)
