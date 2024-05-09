package com.msg.lecture.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate? {
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    return LocalDate.parse(this, formatter)
}