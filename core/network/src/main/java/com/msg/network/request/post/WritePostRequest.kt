package com.msg.network.request.post

import com.msg.model.enumdata.FeedType

data class WritePostRequest(
    val title: String,
    val content: String,
    val links: List<String>,
    val feedType: FeedType
)