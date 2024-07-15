package com.msg.data.mapper.school

import com.msg.model.param.school.PostSchoolParam
import com.msg.network.request.school.PostSchoolRequest

fun PostSchoolParam.toRequest() = PostSchoolRequest(
    schoolName = schoolName,
    line = line,
    department = department,
    logoImage = logoImage
)