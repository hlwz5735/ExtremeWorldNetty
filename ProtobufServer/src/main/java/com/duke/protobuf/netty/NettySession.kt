package com.duke.protobuf.netty

import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.data.NetMessageResponse
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@FunctionalInterface
fun interface ResponseHandler {
    operator fun invoke(res: NetMessageResponse.Builder)
}

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

    private var responseBuilder: NetMessageResponse.Builder? = null
    private val lock = ReentrantLock()

    fun getResponseBuilder(): NetMessageResponse.Builder {
        if (responseBuilder == null) {
            lock.withLock {
                if (responseBuilder == null) {
                    responseBuilder = NetMessageResponse.newBuilder()
                }
            }
        }
        return responseBuilder!!
    }

    fun sendSync(func: ResponseHandler) {
        func(getResponseBuilder())
        sendAndFlush()
    }

    fun sendAsync(func: ResponseHandler) {
        func(getResponseBuilder())
    }

    /** 直接发送消息体 */
    fun sendAndFlush() {
        if (responseBuilder == null) {
            return
        }
        // 后置处理
        user.postProcess()
        val msg = NetMessage.newBuilder().setResponse(getResponseBuilder()).build()
        channel.writeAndFlush(msg)
        responseBuilder = null
    }

    fun isClose(): Boolean {
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
