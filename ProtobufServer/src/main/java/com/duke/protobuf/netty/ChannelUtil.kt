package com.duke.protobuf.netty

import io.netty.channel.Channel
import java.net.InetSocketAddress

object ChannelUtil {
    fun getIp(channel: Channel): String {
        return (channel.remoteAddress() as InetSocketAddress).address.toString().substring(1)
    }
}
