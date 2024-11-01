package com.duke.protobuf.server.modules.chat.service

import com.duke.protobuf.data.ProtoMessages
import com.duke.protobuf.data.ProtoMessages.ChatResponse
import com.duke.protobuf.data.ProtoMessages.NChatMessage
import com.duke.protobuf.server.GameProperties
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class ChatService(
    val gameProps: GameProperties
) {
    private val worldChats = Collections.synchronizedList(ArrayList<NChatMessage>())
    private val systemChats = Collections.synchronizedList(ArrayList<NChatMessage>())
    private val localChatDic = ConcurrentHashMap<Int, MutableList<NChatMessage>>()
    private val teamChatDic = ConcurrentHashMap<Int, MutableList<NChatMessage>>()
    private val guildChatDic = ConcurrentHashMap<Int, MutableList<NChatMessage>>()

    fun getWorldMessages(msgIdx: Int): ChatInfo {
        return fetchChatMessages(worldChats, msgIdx)
    }

    fun getSystemMessages(msgIdx: Int): ChatInfo {
        return fetchChatMessages(systemChats, msgIdx)
    }

    fun getLocalMessages(mapId: Int, msgIdx: Int): ChatInfo {
        val container = localChatDic.getOrPut(mapId) { Collections.synchronizedList(ArrayList()) }
        return fetchChatMessages(container, msgIdx)
    }

    fun getTeamMessages(teamId: Int, msgIdx: Int): ChatInfo {
        val container = teamChatDic.getOrPut(teamId) { Collections.synchronizedList(ArrayList()) }
        return fetchChatMessages(container, msgIdx)
    }

    fun getGuildMessages(guildId: Int, msgIdx: Int): ChatInfo {
        val container = guildChatDic.getOrPut(guildId) { Collections.synchronizedList(ArrayList()) }
        return fetchChatMessages(container, msgIdx)
    }

    private fun fetchChatMessages(container: List<NChatMessage>, msgIdx: Int): ChatInfo {
        if (msgIdx > container.size) {
            return ChatInfo(container.size, emptyList())
        }
        val msgs = container.subList(msgIdx, container.size)
            .filter { it.time > System.currentTimeMillis() - gameProps.messageFetchPeriod }
            .toList()
        return if (msgs.size <= gameProps.messageFetchSize) {
            ChatInfo(container.size, msgs)
        } else {
            ChatInfo(container.size, msgs.subList(msgs.size - gameProps.messageFetchSize, msgs.size))
        }
    }

    fun sendPrivateChat(msg: NChatMessage, from: PlayerCharacter, to: PlayerCharacter) {
        logger.info("发送私信，from【{}-{}】 to 【{}-{}】，内容【{}】", from.id, from.name, to.id, to.name, msg.message)
        to.session.sendSync {
            val chatBuilder = ChatResponse.newBuilder()
            chatBuilder.setResult(ProtoMessages.RESULT.SUCCESS)
            chatBuilder.addPrivateChatMessages(msg)
            it.setChat(chatBuilder)
        }
    }

    fun addMessage(fromChar: PlayerCharacter, originMsg: NChatMessage) {
        logger.info("添加消息 - 频道：{}, 来自：{}, 内容：{}", originMsg.channel, fromChar.name, originMsg.message)
        val msgBuilder = NChatMessage.newBuilder(originMsg)
        msgBuilder.time = System.currentTimeMillis()
        msgBuilder.fromId = fromChar.dbId
        msgBuilder.fromName = fromChar.name
        val realChatMessage = msgBuilder.build()
        when (realChatMessage.channel) {
            NChatMessage.CHAT_CHANNEL.SYSTEM -> {
                systemChats.add(realChatMessage)
            }
            NChatMessage.CHAT_CHANNEL.WORLD -> {
                worldChats.add(realChatMessage)
            }
            NChatMessage.CHAT_CHANNEL.LOCAL -> {
                val container = localChatDic.getOrPut(fromChar.mapId) { Collections.synchronizedList(ArrayList()) }
                container.add(realChatMessage)
            }
            NChatMessage.CHAT_CHANNEL.TEAM -> {
                val team = fromChar.team ?: return
                val container = teamChatDic.getOrPut(team.id) { Collections.synchronizedList(ArrayList()) }
                container.add(realChatMessage)
            }
            NChatMessage.CHAT_CHANNEL.GUILD -> {
                val guild = fromChar.guild ?: return
                val container = guildChatDic.getOrPut(guild.id) { Collections.synchronizedList(ArrayList()) }
                container.add(realChatMessage)
            }
            else -> return
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ChatService::class.java)

        data class ChatInfo(
            val idx: Int,
            val messages: List<NChatMessage>,
        )
    }
}
