package com.duke.protobuf.server

import com.duke.proto.data.*
import com.duke.protobuf.server.util.MessageUtil
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

/*    val character = NCharacterInfo.newBuilder()
        .setId(1)
        .setClass_(CHARACTER_CLASS.ARCHER)
        .setLevel(1)
        .setType(CHARACTER_TYPE.Player)
        .setName("黄忠")
        .setMapId(1)
        .build()
    val builder = UserLoginResponse.newBuilder()
    builder.setErrormsg("None")
        .setResult(RESULT.SUCCESS)
        .userinfoBuilder
        .setId(1)
        .playerBuilder
        .setId(1)
        .addCharacters(character)
    println(MessageUtil.genResponseMessage(builder.build()))*/

    val context: ApplicationContext = SpringApplication.run(ProtobufServerApplication::class.java, *args)
    val endpoint = context.getBean(ProtobufServer::class.java)

    val future = endpoint.start(InetSocketAddress(port))

    Runtime.getRuntime().addShutdownHook(Thread {
        endpoint.destroy()
    })

    future.channel().closeFuture().syncUninterruptibly()
}
