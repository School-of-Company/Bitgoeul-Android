package com.msg.model.remote.model.auth

import com.msg.model.remote.enumdatatype.Authority
import kotlinx.datetime.LocalDateTime


data class AuthTokenModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
    val authority: Authority
)
