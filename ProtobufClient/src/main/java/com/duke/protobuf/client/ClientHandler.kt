package com.duke.protobuf.client

import com.duke.protobuf.data.ProtoMessages.*
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
        val response = responseMsg.response.userLogin
        if (response.result == RESULT.SUCCESS) {
            println("请求成功！")
        } else {
            println("请求失败： ${response.errormsg}")
        }
    }
}
