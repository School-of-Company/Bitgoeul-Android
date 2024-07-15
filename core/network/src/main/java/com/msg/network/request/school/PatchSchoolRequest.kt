package com.msg.network.request.school

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.MultipartBody

@JsonClass(generateAdapter = true)
data class PatchSchoolRequest(
    @Json(name = "schoolName") val schoolName: String,
    @Json(name = "line") val line: String,
    @Json(name = "logoImage") val logoImage: MultipartBody.Part
)