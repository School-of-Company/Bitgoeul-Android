package com.msg.network.request.auth

import com.msg.model.enumdata.HighSchool
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpJobClubTeacherRequest(
    @Json(name = "email") val email: String,
    @Json(name = "name") val name: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "password") val password: String,
    @Json(name = "highSchool") val highSchool: HighSchool,
    @Json(name = "clubName") val clubName: String,
)
