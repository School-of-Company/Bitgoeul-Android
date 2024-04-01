package com.msg.model.remote.response.club

import java.util.UUID

data class ClubDetailResponse(
    val clubId: Long,
    val clubName: String,
    val highSchoolName: String,
    val headCount: Int,
    val students: List<Student>,
    val teacher: Teacher
) {
    data class Student(
        val userId: UUID,
        val id: UUID,
        val name: String
    )

    data class Teacher(
        val id: UUID,
        val name: String
    )
}
