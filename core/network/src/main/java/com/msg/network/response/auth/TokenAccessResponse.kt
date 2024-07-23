package com.msg.network.response.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenAccessResponse(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "accessExpiredAt") val accessExpiredAt: String,
    @Json(name = "refreshExpiredAt") val refreshExpiredAt: String,
)
