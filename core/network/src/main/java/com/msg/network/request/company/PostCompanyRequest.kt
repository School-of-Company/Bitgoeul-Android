package com.msg.network.request.company

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostCompanyRequest(
    @Json(name = "companyName") val companyName: String,
    @Json(name = "field") val field: String
)