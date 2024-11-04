package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.data.ProtoMessages.CHARACTER_CLASS

data class MountDefine(
    val id: Int,
    val name: String,
    val description: String,
    val level: Int,
    val limitClass: CHARACTER_CLASS,
    val icon: String,
    val resource: String,
)
