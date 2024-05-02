package com.msg.lecture.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toLocalTime(): LocalTime {
    val timeString = this
    return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"))
}