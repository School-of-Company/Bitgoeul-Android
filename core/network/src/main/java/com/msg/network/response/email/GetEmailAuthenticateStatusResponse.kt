package com.msg.network.response.email

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetEmailAuthenticateStatusResponse(
    @Json(name = "isAuthentication") val isAuthentication: Boolean,
)
