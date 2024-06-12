package com.msg.model.model.lecture

import java.time.LocalDate
import java.time.LocalTime


data class LectureDates(
    val completeDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)