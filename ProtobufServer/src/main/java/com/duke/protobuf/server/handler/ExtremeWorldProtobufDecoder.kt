package com.duke.protobuf.server.handler

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.CorruptedFrameException

class ExtremeWorldProtobufDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, inBuf: ByteBuf, out: MutableList<Any>) {
        inBuf.markReaderIndex()
        val preIndex = inBuf.readerIndex()
        val length = readLength(inBuf)
        if (preIndex == inBuf.readerIndex()) {
            return
        }
        if (length < 0) {
            throw CorruptedFrameException("negative length: $length")
        }

        if (inBuf.readableBytes() < length) {
            inBuf.resetReaderIndex()
        } else {
            out.add(inBuf.readRetainedSlice(length))
        }
    }

    private fun readLength(inBuf: ByteBuf): Int {
        val length = inBuf.readInt()
        return Integer.reverseBytes(length)
    }
}