package com.msg.model.remote.request.lecture

import com.msg.model.remote.enumdatatype.LectureType
import kotlinx.datetime.LocalDateTime


data class OpenLectureRequest(
    val name: String,
    val content: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val completeDate: LocalDateTime,
    val lectureType: LectureType,
    val credit: Int,
    val maxRegisteredUser: Int,
)
