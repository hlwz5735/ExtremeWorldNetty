package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.modules.user.service.CharacterService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@MessageFacade
class CharacterMessageFacade(
    private val service: CharacterService
) {


    companion object { private val logger = LoggerFactory.getLogger(CharacterMessageFacade::class.java) }
}
