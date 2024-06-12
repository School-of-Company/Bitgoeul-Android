package com.msg.network.response.lecture

import java.time.LocalDate
import java.util.UUID

data class GetLectureSignUpHistoryResponse(
    val lectures: List<SignUpLectures>,
) {
    data class SignUpLectures(
        val id: UUID,
        val name: String,
        val lectureType: String,
        val currentCompletedDate: LocalDate,
        val lecturer: String,
        val isComplete: Boolean,
    )
}