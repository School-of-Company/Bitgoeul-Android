package com.msg.network.request.auth

import com.msg.model.enumdata.HighSchool

data class SignUpCompanyInstructorRequest(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String,
    val company: String
)
