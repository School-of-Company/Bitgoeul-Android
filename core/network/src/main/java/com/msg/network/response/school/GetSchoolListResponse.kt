package com.msg.network.response.school

import com.msg.model.model.school.SchoolModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetSchoolListResponse(
    @Json(name = "schools") val schools: List<SchoolModel>
)
