package com.duke.protobuf.server

import com.duke.protobuf.netty.ExtremeWorldMessageDistributor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.net.InetSocketAddress

@SpringBootApplication
@EnableJpaRepositories
open class ProtobufServerApplication {
    @Bean
    fun extremeWorldMessageDistributor(): ExtremeWorldMessageDistributor {
        return ExtremeWorldMessageDistributor()
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

    Runtime.getRuntime().addShutdownHook(Thread {
        endpoint.destroy()
    })

    future.channel().closeFuture().syncUninterruptibly()
}
