package com.msg.data.mapper.auth

import com.msg.model.entity.auth.AuthTokenEntity
import com.msg.network.response.auth.AuthTokenResponse

fun AuthTokenResponse.toEntity() = AuthTokenEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExpiredAt = accessExpiredAt,
    refreshExpiredAt = refreshExpiredAt,
    authority = authority,
)