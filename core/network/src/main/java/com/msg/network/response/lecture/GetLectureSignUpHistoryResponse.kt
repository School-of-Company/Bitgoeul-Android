package com.msg.network.response.lecture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.util.UUID

@JsonClass(generateAdapter = true)
data class GetLectureSignUpHistoryResponse(
    @Json(name = "lectures") val lectures: List<SignUpLectures>,
) {
    data class SignUpLectures(
        @Json(name = "id") val id: UUID,
        @Json(name = "name") val name: String,
        @Json(name = "lectureType") val lectureType: String,
        @Json(name = "currentCompletedDate") val currentCompletedDate: LocalDate,
        @Json(name = "lecturer") val lecturer: String,
        @Json(name = "isComplete") val isComplete: Boolean,
    )
}