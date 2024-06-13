package com.msg.model.param.auth

import com.msg.model.enumdata.HighSchool

data class SignUpCompanyInstructorParam(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String,
    val company: String
)
