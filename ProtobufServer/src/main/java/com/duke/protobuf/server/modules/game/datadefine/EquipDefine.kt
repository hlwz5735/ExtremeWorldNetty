package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.data.EQUIP_SLOT

data class EquipDefine(
    val id: Int,
    val name: String,
    val slot: EQUIP_SLOT,
    val category: String,
    /** 力量 */
    val strength: Float,
    /** 智力 */
    val intelligence: Float,
    /** 灵巧 */
    val dexterity: Float,
    /** HP */
    val healthPoint: Float,
    /** MP */
    val magicPoint: Float,
    /** 物攻 */
    val atkDamage: Float,
    /** 法伤 */
    val abilityPower: Float,
    /** 物防 */
    val defence: Float,
    /** 法防 */
    val magicDefence: Float,
    /** 攻击速度 */
    val atkSpeed: Float,
    /** 暴击率 */
    val criticalRate: Float,
)
