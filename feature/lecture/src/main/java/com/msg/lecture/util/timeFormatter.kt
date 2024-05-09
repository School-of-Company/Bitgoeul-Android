package com.msg.lecture.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toLocalTime(): LocalTime? {
    val formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초")
    return LocalTime.parse(this, formatter)
}