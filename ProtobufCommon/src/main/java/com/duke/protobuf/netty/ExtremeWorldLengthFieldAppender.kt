package com.duke.protobuf.netty

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class ExtremeWorldLengthFieldAppender : MessageToByteEncoder<ByteBuf>() {
    override fun encode(ctx: ChannelHandlerContext, msg: ByteBuf, out: ByteBuf) {
        val bodyLen = msg.readableBytes()
        val headerLen = 4
        out.ensureWritable(headerLen + bodyLen)
        out.writeInt(Integer.reverseBytes(bodyLen))
        out.writeBytes(msg, msg.readerIndex(), bodyLen)
    }
}