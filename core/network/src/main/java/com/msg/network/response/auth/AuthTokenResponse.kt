package com.msg.network.response.auth

import com.msg.model.enumdata.Authority

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
    val authority: Authority,
)