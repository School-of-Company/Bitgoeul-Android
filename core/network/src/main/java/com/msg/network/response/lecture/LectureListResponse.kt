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
    data class Lectures(
        @Json(name = "content") val content: List<ContentArray>
    )

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
}