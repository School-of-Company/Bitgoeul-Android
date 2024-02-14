package com.msg.model.remote.response.post

import com.msg.model.remote.enumdatatype.FeedType
import kotlinx.datetime.LocalDateTime

data class GetDetailPostResponse(
    val title: String,
    val writer: String,
    val content: String,
    val feedType: FeedType,
    val modifiedAt: LocalDateTime,
    val links: List<String>
)
