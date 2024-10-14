package com.duke.protobuf.server

import com.duke.protobuf.netty.ExtremeWorldMessageDistributor
import com.duke.protobuf.server.modules.map.service.MapService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import java.net.InetSocketAddress

@SpringBootApplication
@EnableJpaRepositories
open class ProtobufServerApplication {
    @Bean
    fun extremeWorldMessageDistributor(): ExtremeWorldMessageDistributor {
        return ExtremeWorldMessageDistributor()
    }
}

var serverEndpoint: ProtobufServer? = null

@Component
class RefreshEventListener : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println("Application context refreshed due to a hot reload")
        // 在此处理应用程序的更改
        // 例如，重新加载某些配置或重新初始化数据等
        serverEndpoint?.destroy()
    }
}

fun main(args: Array<String>) {
    var port = 8888
    if (args.isNotEmpty()) {
        port = args[0].toInt()
    }

    val context: ApplicationContext = SpringApplication.run(ProtobufServerApplication::class.java, *args)
    val endpoint = context.getBean(ProtobufServer::class.java)
    val future = endpoint.start(InetSocketAddress(port))
    serverEndpoint = endpoint

    Runtime.getRuntime().addShutdownHook(Thread {
        val mapService = context.getBean(MapService::class.java)
        mapService.updateThreadRunning = false
        endpoint.destroy()
    })

    future.channel().closeFuture().syncUninterruptibly()
}
