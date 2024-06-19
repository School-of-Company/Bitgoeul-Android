package com.msg.network.response.auth

import com.msg.model.enumdata.Authority
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenResponse(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "accessExpiredAt") val accessExpiredAt: String,
    @Json(name = "refreshExpiredAt") val refreshExpiredAt: String,
    @Json(name = "authority") val authority: Authority,
)