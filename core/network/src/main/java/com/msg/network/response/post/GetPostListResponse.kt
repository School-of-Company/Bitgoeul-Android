package com.msg.network.response.post

import com.msg.model.model.post.PostModel

data class GetPostListResponse(
    val posts: List<PostModel>
)
