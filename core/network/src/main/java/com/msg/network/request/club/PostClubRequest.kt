package com.msg.network.request.club

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostClubRequest(
    @Json(name = "clubName") val clubName: String,
    @Json(name = "field") val field: String
)
