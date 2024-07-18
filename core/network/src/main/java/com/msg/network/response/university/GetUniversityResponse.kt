package com.msg.network.response.university

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUniversityResponse(
    @Json(name = "universities") val universities: List<University>
) {
    @JsonClass(generateAdapter = true)
    data class University(
        @Json(name = "id") val id: Long,
        @Json(name = "universityName") val universityName: String,
        @Json(name = "departments") val departments: List<String>
    )
}