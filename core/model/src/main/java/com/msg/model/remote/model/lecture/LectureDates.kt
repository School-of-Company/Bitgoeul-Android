package com.msg.model.remote.model.lecture

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class LectureDates(
    val completeDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)