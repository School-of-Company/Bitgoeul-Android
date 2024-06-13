package com.msg.network.request.auth

import com.msg.model.enumdata.HighSchool

data class SignUpBbozzakTeacherRequest(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String
)
