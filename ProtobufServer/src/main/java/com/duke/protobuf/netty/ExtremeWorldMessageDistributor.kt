package com.duke.protobuf.netty

import com.duke.protobuf.data.NetMessage
import com.duke.protobuf.data.NetMessageResponse
import com.duke.protobuf.data.RESULT
import com.duke.protobuf.data.UserGameEnterResponse
import com.duke.protobuf.server.annotation.MessageFacade
import com.duke.protobuf.server.annotation.MessageHandler
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.user.service.UserService
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

@ChannelHandler.Sharable
class ExtremeWorldMessageDistributor : SimpleChannelInboundHandler<NetMessage>(), ApplicationContextAware {
    @Autowired
    private lateinit var userService: UserService;

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
                if (handlerMapping != null) {
                    // TODO 这里直接拿参数个数作为判断依据，后续可以实现更加优雅的参数绑定逻辑（参考MVC）
                    if (handlerMapping.method.parameterCount == 1) {
                        handlerMapping.method.invoke(handlerMapping.instance, req)
                    } else {
                        handlerMapping.method.invoke(handlerMapping.instance, req, ctx.channel())
                    }
                } else {
                    null
                }
            }
            // 针对所有正常处理的请求响应体，查找setter并调用以设置值
            .forEach { respData -> findSetterAndSetField(respData, response) }

        do {
            val channel = ctx.channel() ?: break
            val session = SessionUtil.getSessionByChannel<OnlineUser>(channel) ?: break
            val sessionChar = session.user.character ?: break
            val statusNotifies = sessionChar.statusManager.collectChangesToResponse()
            response.setStatusNotify(statusNotifies)
        } while (false)

        val responseMsg = NetMessage.newBuilder()
            .setResponse(response.build())
            .build()
        try {
            ctx.writeAndFlush(responseMsg)
        } catch (ex: Throwable) {
            logger.error("写出数据时发生了异常！", ex)
        }
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

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        do {
            val channel = ctx?.channel() ?: break
            val session = SessionUtil.getSessionByChannel<OnlineUser>(channel) ?: break
            userService.userLeave(session)
        } while (false)
        super.channelInactive(ctx)
        logger.debug("用户连接关闭。")
    }

    /**
     * Spring 上下文初始化完毕后执行，初始化路由信息
     */
    override fun setApplicationContext(ctx: ApplicationContext) {
        val beanMap = ctx.getBeansWithAnnotation(MessageFacade::class.java)
        beanMap.values
            .flatMap(this::extractHandler)
            .forEach {
                handlerMap[it.targetClass] = it
            }
    }

    companion object { private val logger = LoggerFactory.getLogger(ExtremeWorldMessageDistributor::class.java) }
}

class HandlerMapping(
    /** Spring Bean 实例 */
    val instance: Any,
    /** 处理器方法 */
    val method: Method,
    /** 目标请求类 */
    val targetClass: Class<*>
)
