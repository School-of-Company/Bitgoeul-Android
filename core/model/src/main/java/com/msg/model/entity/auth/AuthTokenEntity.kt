package com.msg.model.entity.auth

import com.msg.model.enumdata.Authority


data class AuthTokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
    val authority: Authority
)
