package com.duke.protobuf.server.netty

import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.data.NetMessageResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.data.UserLoginResponse
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
@ChannelHandler.Sharable
class ProtobufServerHandler : SimpleChannelInboundHandler<NetMessage>() {
    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("HelloWorldServerHandler active")
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: NetMessage) {
        println("server channelRead...")
        // 读取客户端发送的数据
        println("客户端发送的数据: $msg")

        val dto = UserLoginResponse.newBuilder()
            .setErrormsg("None")
            .setResult(RESULT.SUCCESS)
            .build()
        val response = NetMessageResponse.newBuilder()
            .setUserLogin(dto)
            .build()
        val responseMsg = NetMessage.newBuilder()
            .setResponse(response)
            .build()

        ctx.writeAndFlush(responseMsg)
    }

    /**
     * 处理异常，关闭通道
     */
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable?) {
        cause?.printStackTrace()
        ctx.channel().close()
    }
}