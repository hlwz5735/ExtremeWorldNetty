package com.duke.protobuf.server

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("com.duke.exw")
data class GameProperties(
    /** 消息拉取批量 */
    var messageFetchSize: Int = 10,
    /** 消息拉取周期（毫秒） */
    var messageFetchPeriod: Long = 5 * 60 * 1000
)
