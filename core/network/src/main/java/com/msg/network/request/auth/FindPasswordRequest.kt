package com.msg.network.request.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FindPasswordRequest(
    @Json(name = "email") val email: String,
    @Json(name = "newPassword") val newPassword: String, // 8 ~ 24 영어(대문자 소문자 상관 X) + 숫자 + 특수 문자(여러 개도 상관 X)
)
