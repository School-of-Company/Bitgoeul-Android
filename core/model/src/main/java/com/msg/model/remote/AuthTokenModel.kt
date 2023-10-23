package com.msg.model.remote

import kotlinx.datetime.LocalDateTime

data class AuthTokenModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String
)
