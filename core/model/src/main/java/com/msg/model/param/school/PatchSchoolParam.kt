package com.msg.model.param.school

import okhttp3.MultipartBody

data class PatchSchoolParam(
    val schoolName: String,
    val line: String,
    val logoImage: MultipartBody.Part
)