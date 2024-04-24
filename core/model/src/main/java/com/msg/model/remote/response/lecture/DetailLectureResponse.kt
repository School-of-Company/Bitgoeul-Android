package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import com.msg.model.remote.model.lecture.LectureDates
import java.time.LocalDate
import java.time.LocalDateTime

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