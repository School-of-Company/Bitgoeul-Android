package com.msg.data.mapper.post

import com.msg.model.param.post.WritePostParam
import com.msg.network.request.post.WritePostRequest

fun WritePostParam.toRequest() = WritePostRequest(
    title = title,
    content = content,
    links = links,
    feedType = feedType,
)