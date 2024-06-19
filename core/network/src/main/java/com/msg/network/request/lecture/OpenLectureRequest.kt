package com.msg.network.request.lecture

import com.msg.model.model.lecture.LectureDates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.UUID

@JsonClass(generateAdapter = true)
data class OpenLectureRequest(
    @Json(name = "name") val name: String,
    @Json(name = "content") val content: String,
    @Json(name = "semester") val semester: String,
    @Json(name = "division") val division: String,
    @Json(name = "department") val department: String,
    @Json(name = "line") val line: String,
    @Json(name = "userId") val userId: UUID,
    @Json(name = "startDate") val startDate: LocalDateTime,
    @Json(name = "endDate") val endDate: LocalDateTime,
    @Json(name = "lectureDates") val lectureDates: List<LectureDates>,
    @Json(name = "lectureType") val lectureType: String,
    @Json(name = "credit") val credit: Int,
    @Json(name = "maxRegisteredUser") val maxRegisteredUser: Int,
    @Json(name = "essentialComplete") val essentialComplete: Boolean,
)