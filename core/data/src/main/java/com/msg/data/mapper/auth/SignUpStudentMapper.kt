package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpStudentParam
import com.msg.network.request.auth.SignUpStudentRequest

fun SignUpStudentParam.toRequest() = SignUpStudentRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
    grade = grade,
    classRoom = classRoom,
    number = number,
    admissionNumber = admissionNumber,
)