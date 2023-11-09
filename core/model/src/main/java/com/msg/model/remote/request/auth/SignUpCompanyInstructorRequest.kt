package com.msg.model.remote.request.auth

import com.msg.model.remote.enumdatatype.HighSchool

data class SignUpCompanyInstructorRequest(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String,
    val company: String
)
