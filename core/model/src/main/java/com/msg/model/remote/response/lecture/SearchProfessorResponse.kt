package com.msg.model.remote.response.lecture

import com.msg.model.remote.enumdatatype.Authority
import java.util.UUID

data class SearchProfessorResponse(
    var instructors: List<Instructors>
)

data class Instructors(
    val id: UUID,
    val name: String,
    val organization: String,
    val authority: Authority,
)
