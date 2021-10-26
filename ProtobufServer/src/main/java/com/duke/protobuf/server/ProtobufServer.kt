package com.duke.protobuf.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.util.concurrent.ImmediateEventExecutor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.InetSocketAddress

@Component
class ProtobufServer {
    private val channelGroup = DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)
    private val group = NioEventLoopGroup()
    private var channel: Channel? = null

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var channelInitializer: ProtobufServerChannelInitializer

    fun start(address: InetSocketAddress): ChannelFuture {
        // 创建服务器起步器
        val b = ServerBootstrap()
        // 指定使用NIO传输通道
        b.group(group)
            // 指定套接字使用的地址和端口
            .channel(NioServerSocketChannel::class.java)
            .childHandler(this.channelInitializer)
        // 绑定调用是异步的，调用 sync() 方法阻塞住等待直至绑定完成
        val future = b.bind(address)
        future.syncUninterruptibly()
        channel = future.channel()
        log.info("服务器启动成功，等待客户端接入……")
        return future
    }

    fun destroy() {
        channel?.close()
        channelGroup.close()
        group.shutdownGracefully()
    }
}
