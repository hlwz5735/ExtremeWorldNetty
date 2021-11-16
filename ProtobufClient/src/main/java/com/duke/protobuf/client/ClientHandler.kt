package com.duke.protobuf.client

import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.data.NetMessageRequest
import com.duke.protobuf.data.UserLoginRequest
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class ClientHandler : ChannelInboundHandlerAdapter() {
    override fun channelActive(ctx: ChannelHandlerContext) {
        println("ClientHandler Active.")
        val dto = UserLoginRequest.newBuilder()
            .setUser("test")
            .setPassward("test")
            .build()
        val request = NetMessageRequest.newBuilder()
            .setUserLogin(dto)
            .build()
        val msg = NetMessage.newBuilder()
            .setRequest(request)
            .build()

        ctx.writeAndFlush(msg)
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        val responseMsg = msg as NetMessage
        println("接收到的消息： $responseMsg")
    }
}