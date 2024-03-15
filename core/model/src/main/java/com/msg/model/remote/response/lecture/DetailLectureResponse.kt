package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class DetailLectureResponse(
    val name: String,
    val content: String,
    val semester: Semester,
    val division: Division,
    val department: String,
    val line: String,
    val createAt: LocalDate,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val lectureDates: List<LectureDates>,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val headCount: Int,
    val maxRegisteredUser: Int,
    val isRegistered: Boolean,
    val lecturer: String,
    val credit: Int,
)

data class LectureDates(
    val completeDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)
