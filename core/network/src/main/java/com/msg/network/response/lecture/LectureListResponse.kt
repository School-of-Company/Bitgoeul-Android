package com.msg.network.response.lecture

import com.msg.model.enumdata.LectureStatus
import com.msg.model.enumdata.Semester
import java.util.UUID

data class LectureListResponse(
    val lectures: Lectures
) {
    data class Lectures(
        val content: List<ContentArray>
    )

    data class ContentArray(
        val id: UUID,
        val name: String,
        val content: String,
        val semester: Semester,
        val division: String,
        val department: String,
        val line: String,
        val startDate: String,
        val endDate: String,
        val lectureType: String,
        val lectureStatus: LectureStatus,
        val headCount: Int,
        val maxRegisteredUser: Int,
        val lecturer: String,
        val essentialComplete: Boolean,
    )
}