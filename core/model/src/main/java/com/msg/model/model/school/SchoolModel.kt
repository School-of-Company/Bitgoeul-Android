package com.msg.model.model.school

import java.util.UUID

data class SchoolModel(
    val id: Long,
    val name: String,
    val line: String,
    val department: List<String>,
    val logoImageUrl: String,
    val clubs: List<Club>
) {
    data class Club(
        val id: Long,
        val name: String,
        val field: String
    )
}