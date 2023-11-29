package com.msg.ui.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.toKoreanFormat(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    return this.format(dateTimeFormatter)
}

fun LocalDateTime.toKoreanFormat(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm")
    return this.format(dateTimeFormatter)
}

fun LocalDate.toDotFormat(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return this.format(dateTimeFormatter)
}