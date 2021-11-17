package com.duke.protobuf.server.modules.game.datadefine

data class MapDefine(
    val id: Int,
    val name: String,
    val type: String,
    val subType: String = "",
    val pkMode: Boolean = false,
    val resource: String = "",
    val minimap: String = "",
    val description: String = ""
)
