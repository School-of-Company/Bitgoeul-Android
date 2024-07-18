package com.msg.model.entity.university

data class GetUniversityEntity(
    val universities: List<University>
) {
    data class University(
        val id: Long,
        val universityName: String,
        val departments: List<String>
    )
}