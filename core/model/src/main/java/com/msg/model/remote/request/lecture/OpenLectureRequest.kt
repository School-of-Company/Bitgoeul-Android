package com.msg.model.remote.request.lecture

import com.msg.model.remote.enumdatatype.LectureType


data class OpenLectureRequest(
    val name: String,
    val content: String,
    val startDate: String,
    val endDate: String,
    val completeDate: String,
    val lectureType: LectureType,
    val credit: Int,
    val maxRegisteredUser: Int,
)
