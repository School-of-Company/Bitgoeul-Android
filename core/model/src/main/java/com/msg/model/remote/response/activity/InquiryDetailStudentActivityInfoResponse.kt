package com.msg.model.remote.response.activity

import com.msg.model.remote.enumdatatype.ApproveStatus
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class InquiryDetailStudentActivityInfoResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val credit: Int,
    val activityDate: LocalDate,
    val modifiedAt: LocalDateTime,
    val approveState: ApproveStatus
)
