package com.msg.data.mapper.auth

import com.msg.model.entity.auth.TokenAccessEntity
import com.msg.network.response.auth.TokenAccessResponse

fun TokenAccessResponse.toEntity() = TokenAccessEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExpiredAt = accessExpiredAt,
    refreshExpiredAt = refreshExpiredAt
)