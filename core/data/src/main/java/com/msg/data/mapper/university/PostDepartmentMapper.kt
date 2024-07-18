package com.msg.data.mapper.university

import com.msg.model.param.university.PostDepartmentParam
import com.msg.network.request.university.PostDepartmentRequest

fun PostDepartmentParam.toRequest() = PostDepartmentRequest(
    department = department
)