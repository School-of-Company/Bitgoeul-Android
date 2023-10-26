package com.msg.model.remote.request

import com.msg.model.remote.HighSchool

data class SignUpJobClubTeacherRequest(
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: HighSchool,
    val clubName: String
)
