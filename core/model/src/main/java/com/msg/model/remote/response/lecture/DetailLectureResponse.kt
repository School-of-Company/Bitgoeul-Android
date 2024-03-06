package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class DetailLectureResponse(
    val id: UUID,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val completeDate: LocalDateTime,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val headCount: Int,
    val maxRegisteredUser: Int,
    val isRegistered: Boolean,
    val lecturer: String,
    val credit: Int,
)
