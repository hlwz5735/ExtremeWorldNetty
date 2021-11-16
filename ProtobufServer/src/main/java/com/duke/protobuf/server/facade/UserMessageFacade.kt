package com.duke.protobuf.server.facade

import com.duke.protobuf.data.*
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import org.springframework.stereotype.Component

@Component
@MessageFacade
class UserMessageFacade {
    @MessageHandler(UserLoginRequest::class)
    fun onUserLogin(request: UserLoginRequest): UserLoginResponse {
        println("server channelRead...")
        // 读取客户端发送的数据
        println("客户端发送的数据: $request")

        return UserLoginResponse.newBuilder()
            .setErrormsg("None")
            .setResult(RESULT.SUCCESS)
            .build()
//        val response = NetMessageResponse.newBuilder()
//            .setUserLogin(dto)
//            .build()
//        val responseMsg = NetMessage.newBuilder()
//            .setResponse(response)
//            .build()
    }
}
