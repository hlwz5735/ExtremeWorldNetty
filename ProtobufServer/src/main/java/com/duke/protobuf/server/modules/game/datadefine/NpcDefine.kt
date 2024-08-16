package com.duke.protobuf.server.modules.game.datadefine

enum class NpcType(val value: Int) {
    None(0),
    Functional(1),
    Task(2)
}

enum class NpcFunction(val value: Int) {
    None(0),
    InvokeShop(1),
    InvokeInstance(2)
}

/**
 * NPC信息的定义
 * （貌似少了地图ID和位置信息，有可能也是生成出来的）
 */
data class NpcDefine(
    val id: Int,
    val name: String,
    val type: NpcType,
    val description: String?,
    val function: NpcFunction?,
    val param: Int?
)
