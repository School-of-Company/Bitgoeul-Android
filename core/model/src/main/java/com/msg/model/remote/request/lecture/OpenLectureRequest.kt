package com.msg.model.remote.request.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


data class OpenLectureRequest(
    val name: String,
    val content: String,
    val semester: Semester,
    val division: Division,
    val department: String,
    val line: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val completeDate: LocalDateTime,
    val lectureType: LectureType,
    val credit: Int,
    val maxRegisteredUser: Int,
)

data class LectureDates(
    val completeDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)