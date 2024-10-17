package com.duke.protobuf.server.util

import java.time.LocalDateTime
import java.time.ZoneId

object DateTimeUtil {
    fun localDateTimeToUnixTimestamp(dateTime: LocalDateTime): Long {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}
