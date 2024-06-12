package com.msg.data.mapper.post

import com.msg.model.entity.post.GetDetailPostEntity
import com.msg.network.response.post.GetDetailPostResponse

fun GetDetailPostResponse.toEntity() = GetDetailPostEntity(
    title = title,
    writer = writer,
    content = content,
    feedType = feedType,
    modifiedAt = modifiedAt,
    links = links
)