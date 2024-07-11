package com.msg.model.param.school

import okhttp3.MultipartBody

data class PostSchoolParam(
    val schoolName: String,
    val line: String,
    val department: List<String>,
    val logoImage: MultipartBody.Part
)
