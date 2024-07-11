package com.msg.network.request.school

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.MultipartBody

@JsonClass(generateAdapter = true)
data class PostSchoolRequest(
    @Json(name = "schoolName") val schoolName: String,
    @Json(name = "line") val line: String,
    @Json(name = "department") val department: List<String>,
    @Json(name = "logoImage") val logoImage: MultipartBody.Part
)
