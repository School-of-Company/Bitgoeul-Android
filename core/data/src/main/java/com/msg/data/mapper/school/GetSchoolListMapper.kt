package com.msg.data.mapper.school

import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.network.response.school.GetSchoolListResponse

fun GetSchoolListResponse.toEntity() = GetSchoolListEntity(
    schools = schools
)