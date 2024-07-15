package com.msg.model.param.lecture

import com.msg.model.model.lecture.LectureDates
import java.time.LocalDateTime
import java.util.UUID


data class OpenLectureParam(
    val name: String,
    val content: String,
    val semester: String,
    val division: String,
    val department: String,
    val line: String,
    val userId: UUID,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val lectureDates: List<LectureDates>,
    val lectureType: String,
    val credit: Int,
    val address: String,
    val locationDetails: String,
    val maxRegisteredUser: Int,
    val essentialComplete: Boolean,
)
