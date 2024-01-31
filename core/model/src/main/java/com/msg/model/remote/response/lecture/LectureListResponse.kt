package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import java.util.UUID

data class LectureListResponse(
    val id: UUID,
    val name: String,
    val content: String,
    val startDate: String,
    val endDate: String,
    val completeDate: String,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val approveStatus: ApproveStatus,
    val headCount: Int,
    val maxRegisteredUser: Int,
    val lecturer: String,
)
