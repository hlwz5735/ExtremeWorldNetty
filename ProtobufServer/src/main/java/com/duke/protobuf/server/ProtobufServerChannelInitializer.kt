package com.duke.protobuf.server

import com.duke.proto.data.NetMessage
import com.duke.protobuf.server.netty.ExtremeWorldLengthFieldAppender
import com.duke.protobuf.server.netty.ExtremeWorldMessageDistributor
import com.duke.protobuf.server.netty.ExtremeWorldProtobufDecoder
import com.duke.protobuf.server.netty.ProtobufServerHandler
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProtobufServerChannelInitializer: ChannelInitializer<Channel>() {
    @Autowired
    private lateinit var distributor: ExtremeWorldMessageDistributor

    override fun initChannel(ch: Channel) {
        val pipeline = ch.pipeline()
        // protobuf 编码器
        pipeline.addLast(ExtremeWorldProtobufDecoder())
        pipeline.addLast("decoder", ProtobufDecoder(NetMessage.getDefaultInstance()))
        pipeline.addLast(ExtremeWorldLengthFieldAppender())
        pipeline.addLast("encoder", ProtobufEncoder())
        pipeline.addLast(distributor)
    }
}
