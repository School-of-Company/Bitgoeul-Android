package com.msg.data.mapper.school

import com.msg.model.param.school.PatchSchoolParam
import com.msg.network.request.school.PatchSchoolRequest

fun PatchSchoolParam.toRequest() = PatchSchoolRequest(
    schoolName = schoolName,
    line = line,
    logoImage = logoImage
)