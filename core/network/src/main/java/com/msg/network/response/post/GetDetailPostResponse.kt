package com.msg.network.response.post

import com.msg.model.enumdata.FeedType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class GetDetailPostResponse(
    @Json(name = "title") val title: String,
    @Json(name = "writer") val writer: String,
    @Json(name = "content") val content: String,
    @Json(name = "feedType") val feedType: FeedType,
    @Json(name = "modifiedAt") val modifiedAt: LocalDateTime,
    @Json(name = "links") val links: List<String>,
)
