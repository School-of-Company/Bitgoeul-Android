package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.SearchDivisionEntity
import com.msg.network.response.lecture.SearchDivisionResponse

fun SearchDivisionResponse.toEntity() = SearchDivisionEntity(
    divisions = divisions
)