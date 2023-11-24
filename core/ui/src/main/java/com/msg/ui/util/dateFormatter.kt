package com.msg.ui.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.toKoreanFormat(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    return this.format(dateTimeFormatter)
}