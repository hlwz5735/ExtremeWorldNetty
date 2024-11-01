package com.duke.protobuf.server.modules.chat.model

import com.duke.protobuf.data.ProtoMessages
import com.duke.protobuf.data.ProtoMessages.ChatResponse
import com.duke.protobuf.server.modules.chat.service.ChatService
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import com.duke.protobuf.server.util.SpringContextUtil

class Chat(
    val owner: PlayerCharacter,
) {
    private val service: ChatService = SpringContextUtil.getBean(ChatService::class.java)!!

    private var localIdx = 0
    private var worldIdx = 0
    private var systemIdx = 0
    private var teamIdx = 0
    private var guildIdx = 0

    fun postProcess() {
        val chatBuilder = ChatResponse.newBuilder()
        chatBuilder.setResult(ProtoMessages.RESULT.SUCCESS)

        // region 设置各种聊天消息字段
        if (owner.mapId != null) {
            val chatInfo = service.getLocalMessages(owner.mapId!!, localIdx)
            localIdx = chatInfo.idx
            chatBuilder.addAllLocalMessages(chatInfo.messages)
        }

        var chatInfo = service.getWorldMessages(worldIdx)
        worldIdx = chatInfo.idx
        chatBuilder.addAllWorldMessages(chatInfo.messages)

        chatInfo = service.getSystemMessages(systemIdx)
        systemIdx = chatInfo.idx
        chatBuilder.addAllSystemMessages(chatInfo.messages)

        val team = owner.team
        if (team != null) {
            chatInfo = service.getTeamMessages(team.id, teamIdx)
            teamIdx = chatInfo.idx
            chatBuilder.addAllTeamMessages(chatInfo.messages)
        }

        val guild = owner.guild
        if (guild != null) {
            chatInfo = service.getGuildMessages(guild.id, guildIdx)
            guildIdx = chatInfo.idx
            chatBuilder.addAllGuildMessages(chatInfo.messages)
        }
        // endregion

        owner.session.sendLazy {
            if (it.hasChat()) {
                chatBuilder.result = it.chat.result
                chatBuilder.errormsg = it.chat.errormsg
                chatBuilder.addAllTeamMessages(it.chat.teamMessagesList)
                chatBuilder.addAllSystemMessages(it.chat.systemMessagesList)
                chatBuilder.addAllLocalMessages(it.chat.localMessagesList)
                chatBuilder.addAllPrivateChatMessages(it.chat.privateChatMessagesList)
                chatBuilder.addAllGuildMessages(it.chat.guildMessagesList)
                chatBuilder.addAllWorldMessages(it.chat.worldMessagesList)
            }
            it.setChat(chatBuilder)
        }
    }
}
