package com.msg.network.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime

fun Any?.toLocalDateTime(): LocalDateTime {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").format(this).toLocalDateTime()
}