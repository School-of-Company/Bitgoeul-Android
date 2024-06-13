package com.msg.network.response.post

import com.msg.model.enumdata.FeedType
import java.time.LocalDateTime

data class GetDetailPostResponse(
    val title: String,
    val writer: String,
    val content: String,
    val feedType: FeedType,
    val modifiedAt: LocalDateTime,
    val links: List<String>
)
