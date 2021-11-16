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

    /**
     * 处理器主体
     *
     * <p>步骤：</p>
     * <ol>
     *     <li>从请求体中取出所有请求类型。</li>
     *     <li>针对每个请求对象，从扫描得到的请求列表中获取处理器。如果能得到则进行下一步的处理。</li>
     *     <li>收集所有得到的响应，聚合形成响应体并返回。</li>
     * </ol>
     */
    override fun channelRead0(ctx: ChannelHandlerContext, msg: NetMessage) {
        val requests = extractRequests(msg)

        val response = NetMessageResponse.newBuilder()

        requests
            .mapNotNull { req ->
                val handlerMapping = handlerMap[req::class.java]
                handlerMapping?.method?.invoke(handlerMapping.instance, req)
            }
            // 针对所有正常处理的请求响应体，查找setter并调用以设置值
            .forEach { respData -> findSetterAndSetField(respData, response) }

        val responseMsg = NetMessage.newBuilder()
            .setResponse(response.build())
            .build()

        ctx.writeAndFlush(responseMsg)
    }

    private fun findSetterAndSetField(respData: Any, response: NetMessageResponse.Builder?) {
        // 这里每次设置值都要进行一次查找，可以考虑按类型将Setter缓存下来
        val setter = NetMessageResponse.Builder::class.java.methods.find { method ->
            method.name.startsWith("set")
                    && method.parameterCount == 1
                    && method.parameterTypes[0].equals(respData::class.java)
        }
        setter?.invoke(response, respData)
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
