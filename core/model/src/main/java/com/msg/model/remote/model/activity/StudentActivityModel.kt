package com.msg.model.remote.model.activity

import java.time.LocalDateTime

data class StudentActivityModel(
    val title: String,
    val content: String,
    val credit: Int,
    val activityDate: LocalDateTime
)
