package com.msg.data.mapper.email

import com.msg.model.entity.email.GetEmailAuthenticateStatusEntity
import com.msg.network.response.email.GetEmailAuthenticateStatusResponse

fun GetEmailAuthenticateStatusResponse.toEntity() = GetEmailAuthenticateStatusEntity(
    isAuthentication = isAuthentication
)