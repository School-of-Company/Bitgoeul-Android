package com.msg.network.response.user

import com.msg.model.enumdata.Authority
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMyPageResponse(
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "authority") val authority: Authority,
    @Json(name = "organization") val organization: String
)
