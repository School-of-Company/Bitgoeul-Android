package com.msg.network.response.lecture

import com.msg.model.enumdata.LectureStatus
import com.msg.model.model.lecture.LectureDates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class DetailLectureResponse(
    @Json(name = "name") val name: String,
    @Json(name = "content") val content: String,
    @Json(name = "semester") val semester: String,
    @Json(name = "division") val division: String,
    @Json(name = "department") val department: String,
    @Json(name = "line") val line: String,
    @Json(name = "createAt") val createAt: LocalDate,
    @Json(name = "startDate") val startDate: LocalDateTime,
    @Json(name = "endDate") val endDate: LocalDateTime,
    @Json(name = "lectureDates") val lectureDates: List<LectureDates>,
    @Json(name = "lectureType") val lectureType: String,
    @Json(name = "lectureStatus") val lectureStatus: LectureStatus,
    @Json(name = "headCount") val headCount: Int,
    @Json(name = "maxRegisteredUser") val maxRegisteredUser: Int,
    @Json(name = "isRegistered") val isRegistered: Boolean,
    @Json(name = "lecturer") val lecturer: String,
    @Json(name = "credit") val credit: Int,
    @Json(name = "essentialComplete") val essentialComplete: Boolean,
)