package com.msg.model.remote.response.lecture

import java.time.LocalDate
import java.util.UUID

data class GetTakingLectureStudentListResponse(
    val students: List<Students>,
)

data class Students(
    val id: UUID,
    val name: String,
    val lectureType: String,
    val currentCompletedDate: LocalDate,
    val lecturer: String,
    val isCompleted: Boolean,
)
