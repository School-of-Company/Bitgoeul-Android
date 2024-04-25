package com.msg.ui.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toLocalTimeFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return this.format(formatter)
}

fun LocalTime.toKoreanFormat(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH시 MM분")
    return this.format(dateTimeFormatter)
}
