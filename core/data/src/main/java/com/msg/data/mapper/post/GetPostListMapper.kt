package com.msg.data.mapper.post

import com.msg.model.entity.post.GetPostListEntity
import com.msg.network.response.post.GetPostListResponse

fun GetPostListResponse.toEntity() = GetPostListEntity(
    posts = posts
)