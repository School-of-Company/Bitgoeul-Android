package com.msg.model.remote.model.lecture

import java.time.LocalDate
import java.time.LocalTime


data class LectureDates(
    val completeDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)