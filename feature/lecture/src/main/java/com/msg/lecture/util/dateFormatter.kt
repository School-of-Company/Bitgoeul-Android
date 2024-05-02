package com.msg.lecture.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate {
    val dateString = this
    return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}