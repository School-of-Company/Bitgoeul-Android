package com.msg.data.mapper.university

import com.msg.model.param.university.PostUniversityParam
import com.msg.network.request.university.PostUniversityRequest

fun PostUniversityParam.toRequest() = PostUniversityRequest(
    universityName = universityName
)