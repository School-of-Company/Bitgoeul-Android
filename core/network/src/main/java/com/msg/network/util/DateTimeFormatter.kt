package com.msg.network.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun Any?.toLocalDateTime(): LocalDateTime? {
    val dateString = this?.toString()
    return when {
        dateString == null -> null
        else -> try {
            when {
                dateString.matches(Regex("\\d{13}")) -> { // Milliseconds -> LocalDateTime 처리
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(dateString.toLong()), ZoneId.systemDefault())
                }
                dateString.contains('.') -> { // 나노초를 포함한 경우 나노초 제거
                    val trimmedDateString = dateString.substring(0, dateString.indexOf('.'))
                    LocalDateTime.parse(trimmedDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                }
                else -> { // 나노초를 포함하지 않은 경우
                    LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                }
            }
        } catch (e: DateTimeParseException) {
            e.printStackTrace()
            null
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            null
        }
    }
    return null
}
