package com.msg.data.mapper.map

import com.msg.model.entity.map.GetLocationEntity
import com.msg.network.response.map.GetLocationResponse

fun GetLocationResponse.toEntity() = GetLocationEntity(
    x = x,
    y = y
)