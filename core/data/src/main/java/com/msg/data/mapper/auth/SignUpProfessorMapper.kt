package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpProfessorParam
import com.msg.network.request.auth.SignUpProfessorRequest

fun SignUpProfessorParam.toRequest() = SignUpProfessorRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
    university = university,
)