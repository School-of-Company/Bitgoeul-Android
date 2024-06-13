package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.SearchLineEntity
import com.msg.network.response.lecture.SearchLineResponse

fun SearchLineResponse.toEntity() = SearchLineEntity(
    lines = lines
)