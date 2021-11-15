package com.duke.protobuf.server.netty

import com.duke.proto.data.NetMessage
import com.duke.proto.data.NetMessageResponse
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

@Component
@ChannelHandler.Sharable
class ExtremeWorldMessageDistributor : SimpleChannelInboundHandler<NetMessage>(), ApplicationContextAware {

    private val handlerMap = ConcurrentHashMap<Class<*>, HandlerMapping>()

    override fun channelRead0(ctx: ChannelHandlerContext, msg: NetMessage) {
        val requests = extractRequests(msg)

        val response = NetMessageResponse.newBuilder()

        requests
            .mapNotNull { req ->
                val handlerMapping = handlerMap[req::class.java]
                handlerMapping?.method?.invoke(req)
            }
            .forEach { respData ->
                val setter = NetMessageResponse::class.java.methods.find { method ->
                    method.name.startsWith("set") && method.returnType.equals(respData::class.java)
                }
                setter?.invoke(response, respData)
            }

        val responseMsg = NetMessage.newBuilder()
            .setResponse(response.build())
            .build()

        ctx.writeAndFlush(responseMsg)
    }

    private fun extractRequests(msg: NetMessage): List<Any> {
        val list = LinkedList<Any>()
        if (msg.request == null) {
            return list
        }

        val request = msg.request

        request::class.java.methods.forEach {
            if (it.name.startsWith("has")) {
                if (it.invoke(request) == true) {
                    val field = it.name.substring(3)
                    val fv = it.invoke("get$field")
                    if (fv != null) {
                        list.add(fv)
                    }
                }
            }
        }

        return list
    }

    override fun setApplicationContext(ctx: ApplicationContext) {
        val beanMap = ctx.getBeansWithAnnotation(MessageFacade::class.java)
        beanMap.entries
            .flatMap(this::extractHandler)
    }

    private fun extractHandler(obj: Any): List<HandlerMapping> {
        val map = HashMap<String, Method>()

        var clazz = obj.javaClass
        while (clazz != Object::class.java) {
            clazz.methods.forEach {
                if (it.isAnnotationPresent(MessageHandler::class.java) && !map.containsKey(it.name)) {
                    map[it.name] = it
                }
            }

            clazz = clazz.superclass
        }

        return map
            .values
            .map { HandlerMapping(obj, it) }
    }
}

class HandlerMapping(
    val instance: Any,
    val method: Method
)
