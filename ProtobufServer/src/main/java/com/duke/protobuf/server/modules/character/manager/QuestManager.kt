package com.duke.protobuf.server.modules.character.manager

import com.duke.protobuf.data.ProtoMessages.NQuestInfo
import com.duke.protobuf.server.modules.character.dbentity.TCharacterQuest
import com.duke.protobuf.server.modules.character.service.CharacterService
import com.duke.protobuf.server.modules.character.service.QuestService
import com.duke.protobuf.server.modules.game.datadefine.QuestTarget
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.modules.game.manager.DataDefineManager
import com.duke.protobuf.server.util.SpringContextUtil
import com.duke.protobuf.structure.DTuple

class QuestManager(
    private val owner: PlayerCharacter,
) {
    private val service = SpringContextUtil.getBean(QuestService::class.java)!!
    private val characterService = SpringContextUtil.getBean(CharacterService::class.java)!!
    private val dataManager = SpringContextUtil.getBean(DataDefineManager::class.java)!!

    fun buildQuestInfos(): List<NQuestInfo> {
        val list: List<TCharacterQuest> = service.listByCharacterId(owner.dbId)
        return list.map(::toNQuestNfo)
    }

    private fun toNQuestNfo(tQuest: TCharacterQuest): NQuestInfo {
        val builder = NQuestInfo.newBuilder()
            .setQuestId(tQuest.questId)
            .setQuestGuid(tQuest.id!!)
            .setStatus(tQuest.status)

        if (tQuest.target1 != null) {
            builder.setTargets(0, tQuest.target1!!)
        }
        if (tQuest.target2 != null) {
            builder.setTargets(1, tQuest.target2!!)
        }
        if (tQuest.target3 != null) {
            builder.setTargets(2, tQuest.target3!!)
        }
        return builder.build()
    }

    fun acceptQuest(questId: Int): DTuple<Boolean, Any> {
        val questDefine = dataManager.questDic[questId] ?: return DTuple(false, "任务定义不存在！")
        val tQuest = TCharacterQuest(
            questId = questId,
            owner = owner.tableData,
        )
        // 如果任务目标1为空，直接标记任务完成，否则标记任务进行中
        if (questDefine.target1 == QuestTarget.None) {
            tQuest.status = NQuestInfo.QUEST_STATUS.COMPLETED
        } else {
            tQuest.status = NQuestInfo.QUEST_STATUS.IN_PROGRESS
        }

        service.save(tQuest)
        return DTuple(true, toNQuestNfo(tQuest))
    }

    fun submitQuest(questId: Int): DTuple<Boolean, Any> {
        val questDefine = dataManager.questDic[questId] ?: return DTuple(false, "任务定义不存在！")
        val tQuest = service.getByCharacterAndDefineId(owner.dbId, questId) ?: return DTuple(false, "当前角色身上没有该任务！")
        if (tQuest.status != NQuestInfo.QUEST_STATUS.COMPLETED) {
            return DTuple(false, "任务尚未完成")
        }
        tQuest.status = NQuestInfo.QUEST_STATUS.FINISHED
        service.save(tQuest)

        // 处理任务奖励
        if (questDefine.rewardGold != null && questDefine.rewardGold > 0) {
            owner.tableData.carriedMoney += questDefine.rewardGold
            // 不忘发出状态变更
            owner.statusManager.modifyMoney(questDefine.rewardGold)
        }
        /*if (questDefine.rewardExp != null && questDefine.rewardExp > 0) {
            owner.tableData.exp += questDefine.rewardExp
        }*/
        characterService.save(owner.tableData)

        if (questDefine.rewardItem1 != null && questDefine.rewardItem1 > 0) {
            owner.itemManager.giveItem(questDefine.rewardItem1, questDefine.rewardItem1Count ?: 0)
        }
        if (questDefine.rewardItem2 != null && questDefine.rewardItem2 > 0) {
            owner.itemManager.giveItem(questDefine.rewardItem2, questDefine.rewardItem2Count ?: 0)
        }
        if (questDefine.rewardItem3 != null && questDefine.rewardItem3 > 0) {
            owner.itemManager.giveItem(questDefine.rewardItem3, questDefine.rewardItem3Count ?: 0)
        }

        return DTuple(true, toNQuestNfo(tQuest))
    }
}
