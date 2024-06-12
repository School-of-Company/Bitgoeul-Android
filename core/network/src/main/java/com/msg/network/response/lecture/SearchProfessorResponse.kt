package com.msg.network.response.lecture

import com.msg.model.enumdata.Authority
import java.util.UUID

data class SearchProfessorResponse(
    var instructors: List<Instructors>
) {
    data class Instructors(
        val id: UUID,
        val name: String,
        val organization: String,
        val authority: Authority,
    )
}