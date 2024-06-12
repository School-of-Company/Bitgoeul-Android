package com.msg.model.entity.activity

import com.msg.model.enumdata.ApproveStatus
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class GetDetailStudentActivityInfoEntity(
    val id: UUID,
    val title: String,
    val content: String,
    val credit: Int,
    val activityDate: LocalDate,
    val modifiedAt: LocalDateTime,
    val approveState: ApproveStatus
)