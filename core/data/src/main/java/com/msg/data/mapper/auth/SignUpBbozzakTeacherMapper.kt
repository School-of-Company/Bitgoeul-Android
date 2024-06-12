package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpBbozzakTeacherParam
import com.msg.network.request.auth.SignUpBbozzakTeacherRequest

fun SignUpBbozzakTeacherParam.toRequest() = SignUpBbozzakTeacherRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
)