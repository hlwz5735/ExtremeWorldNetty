package com.duke.protobuf.server.annotation

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class MessageHandler(
    val messageClass: KClass<*>
)
