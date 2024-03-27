package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import java.util.UUID

data class LectureListResponse(
    val lectures: Lectures
)

data class Lectures(
    val content: List<ContentArray>
)

data class ContentArray(
    val id: UUID,
    val name: String,
    val content: String,
    val semester: Semester,
    val division: Division,
    val department: String,
    val startDate: String,
    val endDate: String,
    val completeDate: String,
    val lectureType: LectureType,
    val lectureStatus: LectureStatus,
    val headCount: Int,
    val maxRegisteredUser: Int,
    val lecturer: String
)
