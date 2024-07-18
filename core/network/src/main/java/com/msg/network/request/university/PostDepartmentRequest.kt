package com.msg.network.request.university

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDepartmentRequest(
    @Json(name = "department") val department: String
)