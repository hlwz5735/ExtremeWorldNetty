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
                handlerMapping?.method?.invoke(handlerMapping.instance, req)
            }
            .forEach { respData ->
                val setter = NetMessageResponse.Builder::class.java.methods.find { method ->
                    method.name.startsWith("set")
                            && method.parameterCount == 1
                            && method.parameterTypes[0].equals(respData::class.java)
                }
                setter?.invoke(response, respData)
            }

        val responseMsg = NetMessage.newBuilder()
            .setResponse(response.build())
            .build()

        ctx.writeAndFlush(responseMsg)
    }

    private fun extractRequests(msg: NetMessage): List<Any> {
        if (msg.request == null) {
            return emptyList()
        }
        return msg.request.allFields.values.toList()
    }

    override fun setApplicationContext(ctx: ApplicationContext) {
        val beanMap = ctx.getBeansWithAnnotation(MessageFacade::class.java)
        beanMap.values
            .flatMap(this::extractHandler)
            .forEach {
                handlerMap[it.targetClass] = it
            }
    }

    private fun extractHandler(obj: Any): List<HandlerMapping> {
        val map = HashMap<String, HandlerMapping>()

        var clazz = obj::class.java
        while (clazz != Object::class.java) {
            clazz.methods.forEach {
                if (it.isAnnotationPresent(MessageHandler::class.java) && !map.containsKey(it.name)) {
                    val annotation = it.getAnnotation(MessageHandler::class.java)
                    map[it.name] = HandlerMapping(obj, it, annotation.messageClass.java)
                }
            }

            clazz = clazz.superclass
        }

        return map.values.toList()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}

class HandlerMapping(
    /** Spring Bean 实例 */
    val instance: Any,
    /** 处理器方法 */
    val method: Method,
    /** 目标请求类 */
    val targetClass: Class<*>
)
