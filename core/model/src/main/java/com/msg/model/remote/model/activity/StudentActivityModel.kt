package com.msg.model.remote.model.activity

import java.time.LocalDate

data class StudentActivityModel(
    val title: String,
    val content: String,
    val credit: Int,
    val activityDate: LocalDate
)
