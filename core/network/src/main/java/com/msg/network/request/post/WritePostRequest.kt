package com.msg.network.request.post

import com.msg.model.enumdata.FeedType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WritePostRequest(
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "links") val links: List<String>,
    @Json(name = "feedType") val feedType: FeedType
)