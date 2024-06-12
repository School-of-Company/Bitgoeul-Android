package com.msg.data.mapper.activity

import com.msg.model.entity.activity.GetDetailStudentActivityInfoEntity
import com.msg.network.response.activity.GetDetailStudentActivityInfoResponse

fun GetDetailStudentActivityInfoResponse.toEntity() = GetDetailStudentActivityInfoEntity(
    id = id,
    title = title,
    content = content,
    credit = credit,
    activityDate = activityDate,
    modifiedAt = modifiedAt,
    approveState = approveState
)