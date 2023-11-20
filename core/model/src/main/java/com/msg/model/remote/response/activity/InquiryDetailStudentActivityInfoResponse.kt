package com.msg.model.remote.response.activity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class InquiryDetailStudentActivityInfoResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val credit: String,
    val activityDate: LocalDate,
    val modifiedAt: LocalDateTime
)
