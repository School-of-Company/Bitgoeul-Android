package com.msg.model.remote.model.lecture

import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import java.util.UUID

data class DetailLectureModel(
    val id: UUID,
    val name: String,
    val startDate: String,
    val endDate: String,
    val completeDate: String,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val headCount: Int,
    val maxRegisteredUser: String,
    val lecturer: String,
    val credit: Int,
)
