package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.HighSchool
import java.util.UUID

data class GetTakingLectureStudentListResponse(
    val students: List<Students>,
)

data class Students(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val classNumber: Int,
    val number: Int,
    val phoneNumber: String,
    val school: HighSchool,
    val clubName: String,
    val cohort: Int,
    val isCompleted: Boolean,
)
