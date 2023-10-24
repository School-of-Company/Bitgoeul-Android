package com.msg.network.util

import kotlinx.datetime.LocalDateTime
import java.time.format.DateTimeFormatter

fun Any?.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.parse(toString())
}