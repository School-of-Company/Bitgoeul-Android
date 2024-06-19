package com.msg.network.response.lecture

import com.msg.model.enumdata.Authority
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class SearchProfessorResponse(
    @Json(name = "instructors") var instructors: List<Instructors>,
) {
    data class Instructors(
        @Json(name = "id") val id: UUID,
        @Json(name = "name") val name: String,
        @Json(name = "organization") val organization: String,
        @Json(name = "authority") val authority: Authority,
    )
}