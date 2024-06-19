package com.msg.network.request.email

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendLinkToEmailRequest(
    @Json(name = "email") val email: String,
)
