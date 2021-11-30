package com.duke.protobuf.netty

import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

class NettySession<T : SessionUser>(
    val channel: Channel,
    var user: T
) {
    private val dispatchKeyGenerator =  AtomicInteger()

    val ipAddr: String = ChannelUtil.getIp(channel)

    var reconnected: Boolean = false

    /** 业务分发索引 */
    val dispatchKey: Int = dispatchKeyGenerator.getAndIncrement()

    /** 其他拓展数据 */
    val attrs: MutableMap<String, Any> = HashMap()

    fun send(data: Any?) {
        if (data == null) {
            return
        }
        channel.writeAndFlush(data)
    }

    fun isCLose(): Boolean {
        return !channel.isActive || channel.isOpen
    }

    fun close(reason: SessionCloseReason) {
        try {
            if (channel.isOpen) {
                channel.close()
                logger.info("session[{}] 已关闭，原因是：{}", user.id, reason)
            } else {
                logger.info("session[{}] 已经关闭了，原因是：{}", user.id, reason)
            }
        } catch (e: Exception) {
            logger.error("关闭频道时发生了异常！", e)
        }
    }

    companion object { private val logger = LoggerFactory.getLogger(NettySession::class.java)}
}

enum class SessionCloseReason {
    /** 正常退出 */
    NORMAL,
    /** 链接超时 */
    OVER_TIME
}
