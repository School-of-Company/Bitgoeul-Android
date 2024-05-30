package com.msg.model.remote.model.auth

import Authority


data class AuthTokenModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
    val authority: Authority
)
