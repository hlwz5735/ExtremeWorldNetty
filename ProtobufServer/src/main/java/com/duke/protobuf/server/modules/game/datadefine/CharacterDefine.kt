package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.data.CHARACTER_CLASS

data class CharacterDefine(
    val tid: Int,

    /** 角色定义的名字 */
    val name: String,

    val type: String,

    /** 角色的职业 */
    val clazz: CHARACTER_CLASS,

    /** 角色定义对应的资源 */
    val resource: String,

    /** 角色定义的描述 */
    val description: String?,

    val initLevel: Int,

    /** 速度属性 */
    val speed: Int = 0
)
