package com.msg.network.request.university

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostUniversityRequest(
    @Json(name = "universityName") val universityName: String
)