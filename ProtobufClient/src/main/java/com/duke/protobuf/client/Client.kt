package com.duke.protobuf.client

import com.duke.proto.data.NetMessage
import com.duke.protobuf.server.netty.ExtremeWorldLengthFieldAppender
import com.duke.protobuf.server.netty.ExtremeWorldProtobufDecoder
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder

class Client(private val host: String, private val port: Int) {
    fun start() {
        val group = NioEventLoopGroup()
        try {
            val client = Bootstrap()
                .group(group)
                .channel(NioSocketChannel::class.java)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel) {
                        ch.pipeline()
                            .addLast(ExtremeWorldProtobufDecoder())
                            .addLast("decoder", ProtobufDecoder(NetMessage.getDefaultInstance()))
                            .addLast(ExtremeWorldLengthFieldAppender())
                            .addLast("encoder", ProtobufEncoder())
                            .addLast(ClientHandler())
                    }
                })
            client.connect(host, port)
                .sync()
                .channel()
                .closeFuture()
                .sync()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            group.shutdownGracefully()
        }
    }
}

fun main(args: Array<String>) {
    val HOST = "localhost"
    val PORT = 8888

    var host = HOST
    var port = PORT
    if (args.size >= 2) {
        host = args[0]
        port = args[1].toInt()
    }

    Client(host, port).start()
}
