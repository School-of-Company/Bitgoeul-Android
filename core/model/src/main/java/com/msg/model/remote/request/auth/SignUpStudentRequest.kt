package com.msg.model.remote.request.auth

import com.msg.model.remote.enumdatatype.HighSchool

data class SignUpStudentRequest (
    val email: String,
    val name: String,
    val phoneNumber: String,
    val password: String,
    val highSchool: String,
    val clubName: String,
    val grade: Int,
    val classRoom: Int,
    val number: Int,
    val admissionNumber: Int
)