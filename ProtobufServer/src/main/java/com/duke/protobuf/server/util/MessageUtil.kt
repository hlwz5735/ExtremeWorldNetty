package com.duke.protobuf.server.util

import com.duke.protobuf.data.ProtoMessages.*

object MessageUtil {
    fun genRequestMessage(): NetMessage {
        return NetMessage.newBuilder()
            .setRequest(NetMessageRequest.newBuilder().build())
            .build()
    }

    fun genResponseMessage(): NetMessage {
        return NetMessage.newBuilder()
            .setResponse(NetMessageResponse.newBuilder().build())
            .build()
    }

    fun genResponseMessage(data: UserLoginResponse): NetMessage {
        val builder = NetMessage.newBuilder()
        builder.responseBuilder.userLogin = data
        return builder.build()
    }
}
