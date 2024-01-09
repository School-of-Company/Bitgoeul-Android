package com.msg.model.remote.response.post

import com.msg.model.remote.model.post.PostModel

data class GetPostListResponse(
    val posts: List<PostModel>
)
