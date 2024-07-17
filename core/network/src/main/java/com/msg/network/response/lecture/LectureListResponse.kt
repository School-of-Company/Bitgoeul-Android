package com.msg.network.response.lecture

import com.msg.model.enumdata.LectureStatus
import com.msg.model.enumdata.Semester
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class LectureListResponse(
    @Json(name = "lectures") val lectures: Lectures
) {
    @JsonClass(generateAdapter = true)
    data class Lectures(
        @Json(name = "content") val content: List<ContentArray>,
        @Json(name = "pageable") val pageable: Pageable,
        @Json(name = "last") val last: Boolean,
        @Json(name = "totalPages") val totalPages: Int,
        @Json(name = "totalElements") val totalElements: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "number") val number: Int,
        @Json(name = "first") val first: Boolean,
        @Json(name = "sort") val sort: Sort,
        @Json(name = "numberOfElements") val numberOfElements: Int,
        @Json(name = "empty") val empty: Boolean
    ) {
        @JsonClass(generateAdapter = true)
        data class ContentArray(
            @Json(name = "id") val id: UUID,
            @Json(name = "name") val name: String,
            @Json(name = "content") val content: String,
            @Json(name = "semester") val semester: Semester,
            @Json(name = "division") val division: String,
            @Json(name = "department") val department: String,
            @Json(name = "line") val line: String,
            @Json(name = "startDate") val startDate: String,
            @Json(name = "endDate") val endDate: String,
            @Json(name = "lectureType") val lectureType: String,
            @Json(name = "lectureStatus") val lectureStatus: LectureStatus,
            @Json(name = "headCount") val headCount: Int,
            @Json(name = "maxRegisteredUser") val maxRegisteredUser: Int,
            @Json(name = "lecturer") val lecturer: String,
            @Json(name = "essentialComplete") val essentialComplete: Boolean,
        )

        @JsonClass(generateAdapter = true)
        data class Pageable(
            @Json(name = "sort") val sort: Sort,
            @Json(name = "offset") val offset: Long,
            @Json(name = "pageNumber") val pageNumber: Int,
            @Json(name = "pageSize") val pageSize: Int,
            @Json(name = "paged") val paged: Boolean,
            @Json(name = "unpaged") val unpaged: Boolean
        )

        @JsonClass(generateAdapter = true)
        data class Sort(
            @Json(name = "empty") val empty: Boolean,
            @Json(name = "sorted") val sorted: Boolean,
            @Json(name = "unsorted") val unsorted: Boolean
        )
    }
}