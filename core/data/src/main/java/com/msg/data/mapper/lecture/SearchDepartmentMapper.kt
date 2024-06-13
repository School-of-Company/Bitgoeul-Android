package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.SearchDepartmentEntity
import com.msg.network.response.lecture.SearchDepartmentResponse

fun SearchDepartmentResponse.toEntity() = SearchDepartmentEntity(
    departments = departments
)