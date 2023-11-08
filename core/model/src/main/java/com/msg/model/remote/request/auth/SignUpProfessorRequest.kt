package com.msg.model.remote.request.auth

import com.msg.model.remote.HighSchool

data class SignUpProfessorRequest(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String,
    val university: String
)
