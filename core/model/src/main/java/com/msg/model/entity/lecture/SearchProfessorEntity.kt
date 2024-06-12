package com.msg.model.entity.lecture

import com.msg.model.enumdata.Authority
import java.util.UUID

data class SearchProfessorEntity(
    var instructors: List<Instructors>
)

data class Instructors(
    val id: UUID,
    val name: String,
    val organization: String,
    val authority: Authority,
)
