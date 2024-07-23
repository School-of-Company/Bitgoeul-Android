package com.msg.model.entity.auth

data class TokenAccessEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
)
