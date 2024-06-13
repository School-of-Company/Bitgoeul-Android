package com.msg.data.mapper.user

import com.msg.model.entity.user.InquiryMyPageEntity
import com.msg.network.response.user.InquiryMyPageResponse

fun InquiryMyPageResponse.toEntity() = InquiryMyPageEntity(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    authority = authority,
    organization = organization
)