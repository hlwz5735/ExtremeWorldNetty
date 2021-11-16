package com.duke.protobuf.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import java.net.InetSocketAddress

@SpringBootApplication
open class ProtobufServerApplication {}

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