package com.msg.network.response.map

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetLocationResponse(
    @Json(name = "x") val x: String,
    @Json(name = "y") val y: String
)