package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.data.RESULT
import com.duke.protobuf.data.UserCreateCharacterRequest
import com.duke.protobuf.data.UserCreateCharacterResponse
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.user.service.CharacterService
import com.duke.protobuf.server.net.pojo.OnlineUser
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class CharacterMessageFacade(
    private val service: CharacterService
) {


    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}
