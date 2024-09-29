package com.duke.protobuf.server.modules.game.datadefine

import com.duke.protobuf.data.CHARACTER_CLASS

enum class QuestType {
    MAIN, BRANCH
}
enum class QuestTarget {
    None, Kill, Item
}

data class QuestDefine(
    val id: Int,
    val name: String,
    val limitLevel: Int,
    val limitClass: CHARACTER_CLASS,
    val preQuest: Int?,
    val postQuest: Int?,
    val type: QuestType,
    val acceptNpc: Int,
    val submitNpc: Int,
    val overview: String,

    val dialog: String,
    val dialogAccept: String,
    val dialogDeny: String,
    val dialogIncomplete: String?,
    val dialogFinish: String,

    val target1: QuestTarget,
    val target1Id: Int,
    val target1Num: Int,
    val target2: QuestTarget,
    val target2Id: Int,
    val target2Num: Int,
    val target3: QuestTarget,
    val target3Id: Int,
    val target3Num: Int,

    val rewardGold: Int?,
    val rewardExp: Int?,

    val rewardItem1: Int?,
    val rewardItem1Count: Int?,
    val rewardItem2: Int?,
    val rewardItem2Count: Int?,
    val rewardItem3: Int?,
    val rewardItem3Count: Int?,
)
