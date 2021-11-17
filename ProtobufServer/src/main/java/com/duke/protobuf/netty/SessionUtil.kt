package com.duke.protobuf.netty

import io.netty.channel.Channel
import io.netty.util.AttributeKey

object SessionUtil {
    val SESSION_KEY: AttributeKey<NettySession<*>> = AttributeKey.valueOf("session")

    fun <T: SessionUser> addSessionToChannel(session: NettySession<T>): Boolean {
        val sessionAttr = session.channel.attr(SESSION_KEY)
        return sessionAttr.compareAndSet(null, session)
    }

    fun <T: SessionUser> getSessionByChannel(channel: Channel): NettySession<T>? {
        val sessionAttr = channel.attr(SESSION_KEY)
        @Suppress("UNCHECKED_CAST")
        return sessionAttr.get() as NettySession<T>?
    }
}
