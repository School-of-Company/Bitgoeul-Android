package com.msg.network.response.club

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudentBelongClubResponse(
    @Json(name = "name") val name: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "email") val email: String,
    @Json(name = "credit") val credit: Int
)
