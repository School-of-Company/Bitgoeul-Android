package com.msg.model.remote.request.lecture

import com.msg.model.remote.model.lecture.LectureDates
import java.time.LocalDateTime
import java.util.UUID


data class OpenLectureRequest(
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
    val maxRegisteredUser: Int,
    val essentialComplete: Boolean,
)
