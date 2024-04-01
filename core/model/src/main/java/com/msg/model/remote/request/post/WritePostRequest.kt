package com.msg.model.remote.request.post

import com.msg.model.remote.enumdatatype.FeedType

data class WritePostRequest(
    val title: String,
    val content: String,
    val links: List<String>,
    val feedType: FeedType
)