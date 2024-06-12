

package com.msg.data.mapper.auth

import com.msg.model.param.auth.SignUpCompanyInstructorParam
import com.msg.network.request.auth.SignUpCompanyInstructorRequest

fun SignUpCompanyInstructorParam.toRequest() = SignUpCompanyInstructorRequest(
    email = email,
    name = name,
    phoneNumber = phoneNumber,
    password = password,
    highSchool = highSchool,
    clubName = clubName,
    company = company,
)