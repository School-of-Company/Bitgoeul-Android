package com.msg.network.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.isDateExpired(): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    return kotlin.runCatching {
        val dateTime = LocalDateTime.parse(this, formatter)
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        dateTime.isBefore(currentTime)
    }.getOrElse {
        true
    }
}