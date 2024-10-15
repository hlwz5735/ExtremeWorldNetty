package com.duke.protobuf.server

import com.duke.protobuf.data.ProtoMessages.NetMessage
import com.duke.protobuf.netty.ExtremeWorldLengthFieldAppender
import com.duke.protobuf.netty.ExtremeWorldMessageDistributor
import com.duke.protobuf.netty.ExtremeWorldProtobufDecoder
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.util.concurrent.DefaultEventExecutorGroup
import io.netty.util.concurrent.EventExecutorGroup
import io.netty.util.concurrent.RejectedExecutionHandlers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadFactory

@Component
class ProtobufServerChannelInitializer: ChannelInitializer<Channel>() {
    @Autowired
    private lateinit var distributor: ExtremeWorldMessageDistributor

    /**
     * 业务线程池
     *
     * 注：线程数量不宜设置过高,线程切换非常耗时
     */
    private val eventExecutorGroup: EventExecutorGroup = DefaultEventExecutorGroup(
        Runtime.getRuntime().availableProcessors() * 2,
        ThreadFactory { r: Runnable ->
            val thread = Thread(r)
            thread.name = "msghandler-" + r.hashCode()
            thread
        },
        100000,
        RejectedExecutionHandlers.reject()
    )

    override fun initChannel(ch: Channel) {
        val pipeline = ch.pipeline()
        // protobuf 编码器
        pipeline.addLast(ExtremeWorldProtobufDecoder())
        pipeline.addLast("decoder", ProtobufDecoder(NetMessage.getDefaultInstance()))
        pipeline.addLast(ExtremeWorldLengthFieldAppender())
        pipeline.addLast("encoder", ProtobufEncoder())
        pipeline.addLast(eventExecutorGroup, distributor)
    }
}
