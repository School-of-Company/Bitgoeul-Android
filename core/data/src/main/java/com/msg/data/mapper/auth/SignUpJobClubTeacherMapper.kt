package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpJobClubTeacherParam
import com.msg.network.request.auth.SignUpJobClubTeacherRequest

fun SignUpJobClubTeacherParam.toRequest() = SignUpJobClubTeacherRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
)