package com.msg.network.request.government

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostGovernmentRequest(
    @Json(name = "field") val field: String,
    @Json(name = "governmentName") val governmentName: String
)