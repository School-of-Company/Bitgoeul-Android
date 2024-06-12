package com.msg.model.param.post

import com.msg.model.enumdata.FeedType

data class WritePostParam(
    val title: String,
    val content: String,
    val links: List<String>,
    val feedType: FeedType
)