package com.msg.model.remote.request.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import com.msg.model.remote.model.lecture.LectureDates
import java.time.LocalDateTime
import java.util.UUID


data class OpenLectureRequest(
    val name: String,
    val content: String,
    val semester: Semester,
    val division: Division,
    val department: String,
    val line: String,
    val userId: UUID,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val lectureDates: List<LectureDates>,
    val lectureType: LectureType,
    val credit: Int,
    val maxRegisteredUser: Int,
)
