package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpGovernmentParam
import com.msg.network.request.auth.SignUpGovernmentRequest

fun SignUpGovernmentParam.toRequest() = SignUpGovernmentRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
    governmentName = governmentName,
    position = position,
    sectors = sectors,
)