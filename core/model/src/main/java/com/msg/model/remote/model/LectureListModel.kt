package com.msg.model.remote.model

import com.msg.model.remote.LectureStatus
import com.msg.model.remote.LectureType
import java.util.UUID

data class LectureListModel(
    val id: UUID,
    val name: String,
    val startDate: String,
    val endDate: String,
    val completeDate: String,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val headCount: Int,
    val maxRegisteredUser: String,
)
