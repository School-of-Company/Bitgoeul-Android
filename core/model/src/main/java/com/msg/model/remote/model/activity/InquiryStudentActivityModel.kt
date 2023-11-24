package com.msg.model.remote.model.activity

import com.msg.model.remote.enumdatatype.ApproveStatus
import java.time.LocalDate
import java.util.UUID

data class InquiryStudentActivityModel (
    val activityId: UUID,
    val title: String,
    val activityDate: LocalDate,
    val userId: UUID,
    val userName: String,
    val approveStatus: ApproveStatus
)