package com.msg.network.response.lecture

import com.msg.model.enumdata.HighSchool
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class GetTakingLectureStudentListResponse(
    @Json(name = "students") val students: List<Students>,
) {
    data class Students(
        @Json(name = "id") val id: UUID,
        @Json(name = "email") val email: String,
        @Json(name = "name") val name: String,
        @Json(name = "grade") val grade: Int,
        @Json(name = "classNumber") val classNumber: Int,
        @Json(name = "number") val number: Int,
        @Json(name = "phoneNumber") val phoneNumber: String,
        @Json(name = "school") val school: HighSchool,
        @Json(name = "clubName") val clubName: String,
        @Json(name = "cohort") val cohort: Int,
        @Json(name = "isCompleted") val isCompleted: Boolean,
    )
}