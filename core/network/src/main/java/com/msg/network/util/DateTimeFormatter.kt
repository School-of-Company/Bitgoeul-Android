package com.msg.network.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun Any?.toLocalDateTime(): LocalDateTime? {
    val dateString = this?.toString()
    if (dateString != null) {
        return try {
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-d'T'HH:mm:ss"))
        } catch (e: DateTimeParseException) {
            e.printStackTrace()
            null
        }
    }
    return null
}
