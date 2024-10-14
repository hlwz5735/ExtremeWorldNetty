package com.duke.protobuf.netty

interface SessionUser {
    val id: Int

    fun postProcess()
}
