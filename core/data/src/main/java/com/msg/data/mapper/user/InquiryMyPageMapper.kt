package com.msg.data.mapper.user

import com.msg.model.entity.user.GetMyPageEntity
import com.msg.network.response.user.GetMyPageResponse

fun GetMyPageResponse.toEntity() = GetMyPageEntity(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    authority = authority,
    organization = organization
)