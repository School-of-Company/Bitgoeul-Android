package com.msg.model.model.activity

import com.msg.model.enumdata.ApproveStatus
import java.time.LocalDate
import java.util.UUID

data class GetStudentActivityModel (
    val activityId: UUID,
    val title: String,
    val activityDate: LocalDate,
    val userId: UUID,
    val userName: String,
    val approveStatus: ApproveStatus
)