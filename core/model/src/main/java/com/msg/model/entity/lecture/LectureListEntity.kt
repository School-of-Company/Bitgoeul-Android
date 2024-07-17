package com.msg.model.entity.lecture

import com.msg.model.enumdata.LectureStatus
import com.msg.model.enumdata.Semester
import java.util.UUID

data class LectureListEntity(
    val lectures: Lectures,
) {

    data class Lectures(
        val content: List<ContentArray>,
        val pageable: Pageable,
        val last: Boolean,
        val totalPages: Int,
        val totalElements: Int,
        val size: Int,
        val number: Int,
        val first: Boolean,
        val sort: Sort,
        val numberOfElements: Int,
        val empty: Boolean,
    ) {
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

        data class Pageable(
            val sort: Sort,
            val offset: Long,
            val pageNumber: Int,
            val pageSize: Int,
            val paged: Boolean,
            val unpaged: Boolean,
        )

        data class Sort(
            val empty: Boolean,
            val sorted: Boolean,
            val unsorted: Boolean,
        )
    }
}