package com.msg.network.response.lecture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchDivisionResponse(
    @Json(name = "divisions") val divisions: List<String>,
)