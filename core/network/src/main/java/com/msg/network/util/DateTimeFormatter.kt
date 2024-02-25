package com.msg.network.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
fun Any?.toLocalDateTime(): LocalDateTime {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").format(this).toLocalDateTime()
}