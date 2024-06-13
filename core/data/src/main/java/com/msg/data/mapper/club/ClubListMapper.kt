package com.msg.data.mapper.club

import com.msg.model.entity.club.ClubListEntity
import com.msg.network.response.club.ClubListResponse

fun ClubListResponse.toEntity() = ClubListEntity(
    id = id,
    name = name,
    schoolName = schoolName
)