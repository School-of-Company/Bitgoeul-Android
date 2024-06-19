package com.msg.network.response.club

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class ClubDetailResponse(
    @Json(name = "clubId") val clubId: Long,
    @Json(name = "clubName") val clubName: String,
    @Json(name = "highSchoolName") val highSchoolName: String,
    @Json(name = "headCount") val headCount: Int,
    @Json(name = "students") val students: List<Student>,
    @Json(name = "teacher") val teacher: Teacher
) {
    data class Student(
        @Json(name = "id") val id: UUID,
        @Json(name = "name") val name: String
    )

    data class Teacher(
        @Json(name = "id") val id: UUID,
        @Json(name = "name") val name: String
    )
}
