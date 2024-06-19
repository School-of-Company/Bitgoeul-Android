package com.msg.network.response.lecture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchLineResponse(
    @Json(name = "lines") val lines: List<String>
)