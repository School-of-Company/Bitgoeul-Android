package com.msg.data.mapper.club

import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.network.response.club.StudentBelongClubResponse

fun StudentBelongClubResponse.toEntity() = StudentBelongClubEntity(
    name = name,
    phoneNumber = phoneNumber,
    email = email,
    credit = credit
)