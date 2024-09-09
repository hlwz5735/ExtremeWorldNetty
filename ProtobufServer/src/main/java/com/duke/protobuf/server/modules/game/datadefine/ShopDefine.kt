package com.duke.protobuf.server.modules.game.datadefine

data class ShopDefine(
    var id: Int = -1,
    var name: String = "",
    var icon: String? = null,
    var description: String? = null,
    var status: Int = 1,
)

data class ShopItemDefine (
    var itemId: Int = -1,
    var count: Int = 0,
    var price: Int = 0,
    var status: Int = 1,
)
