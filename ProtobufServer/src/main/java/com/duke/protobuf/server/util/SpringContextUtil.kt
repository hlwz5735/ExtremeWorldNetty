package com.duke.protobuf.server.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


@Component
class SpringContextUtil : ApplicationContextAware {
    override fun setApplicationContext(ctx: ApplicationContext) {
        applicationContext = ctx
    }

    companion object {
        private lateinit var applicationContext: ApplicationContext

        fun getApplicationContext(): ApplicationContext {
            return applicationContext
        }

        fun <T> getBean(name: String): T? {
            @Suppress("UNCHECKED_CAST")
            return applicationContext.getBean(name) as T?
        }

        fun <T> getBean(tClass: Class<T>): T? {
            return applicationContext.getBean(tClass) as T?
        }
    }
}
