package com.duke.protobuf.server.modules.user.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.user.dbentity.TUser
import com.duke.protobuf.server.modules.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

typealias User = TUser

@Component
@MessageFacade
class UserMessageFacade(private val service: UserService) {
    @MessageHandler(UserLoginRequest::class)
    fun onUserLogin(request: UserLoginRequest): UserLoginResponse {
        logger.info("用户登录请求: 用户名：${request.user}，密码：${request.passward}")

        val result = this.service.checkLogin(request.user, request.passward)
        return if (result.first == true) {
            UserLoginResponse.newBuilder()
                .setErrormsg("None")
                .setResult(RESULT.SUCCESS)
                .build()
        } else {
            UserLoginResponse.newBuilder()
                .setErrormsg(result.second)
                .setResult(RESULT.FAILED)
                .build()
        }
    }

    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}
