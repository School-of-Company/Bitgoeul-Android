package com.msg.data.mapper.admin

import com.msg.model.entity.admin.UseListContentEntity
import com.msg.network.response.admin.UserListContentResponse

fun UserListContentResponse.toEntity() = UseListContentEntity(
    id = id,
    name = name,
    authority = authority,
    approveStatus = approveStatus,
)