package com.msg.model.entity.post

import com.msg.model.enumdata.FeedType
import java.time.LocalDateTime

data class GetDetailPostEntity(
    val title: String,
    val writer: String,
    val content: String,
    val feedType: FeedType,
    val modifiedAt: LocalDateTime,
    val links: List<String>
)
