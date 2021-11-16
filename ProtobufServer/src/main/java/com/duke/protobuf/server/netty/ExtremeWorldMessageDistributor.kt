package com.duke.protobuf.server.netty

import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.data.NetMessageResponse
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.slf4j.LoggerFactory
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
    /** 请求对象类型-请求处理器对象映射表 */
    private val handlerMap = ConcurrentHashMap<Class<*>, HandlerMapping>()

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
        logger.debug("新用户连接加入……")
    }

    /**
     * 处理器主体
     *
     * 步骤：
     * 1. 从请求体中取出所有请求类型。
     * 2. 针对每个请求对象，从扫描得到的请求列表中获取处理器。如果能得到则进行下一步的处理。
     * 3. 收集所有得到的响应，聚合形成响应体并返回。
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

    override fun handlerRemoved(ctx: ChannelHandlerContext?) {
        super.handlerRemoved(ctx)
        logger.debug("用户连接关闭。")
    }

    override fun setApplicationContext(ctx: ApplicationContext) {
        val beanMap = ctx.getBeansWithAnnotation(MessageFacade::class.java)
        beanMap.values
            .flatMap(this::extractHandler)
            .forEach {
                handlerMap[it.targetClass] = it
            }
    }

    companion object { private val logger = LoggerFactory.getLogger(this::class.java) }
}

class HandlerMapping(
    /** Spring Bean 实例 */
    val instance: Any,
    /** 处理器方法 */
    val method: Method,
    /** 目标请求类 */
    val targetClass: Class<*>
)
