package com.msg.model.remote.model.auth

import com.msg.model.remote.enumdatatype.Authority
import java.time.LocalDateTime


data class AuthTokenModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: LocalDateTime,
    val refreshExpiredAt: LocalDateTime,
    val authority: Authority
)
