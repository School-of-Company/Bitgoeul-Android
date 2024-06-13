package com.msg.network.response.activity

import com.msg.model.enumdata.ApproveStatus
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class GetDetailStudentActivityInfoResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val credit: Int,
    val activityDate: LocalDate,
    val modifiedAt: LocalDateTime,
    val approveState: ApproveStatus
)
