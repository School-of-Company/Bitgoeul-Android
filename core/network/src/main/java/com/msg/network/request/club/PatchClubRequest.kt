package com.msg.network.request.club

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatchClubRequest(
    @Json(name = "clubName") val clubName: String,
    @Json(name = "field") val field: String,
    @Json(name = "schoolId") val schoolId: String
)