package com.msg.network.response.post

import com.msg.model.model.post.PostModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPostListResponse(
    @Json(name = "posts") val posts: List<PostModel>,
)
