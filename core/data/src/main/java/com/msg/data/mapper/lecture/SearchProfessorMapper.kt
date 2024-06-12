package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.SearchProfessorEntity
import com.msg.network.response.lecture.SearchProfessorResponse
import com.msg.model.entity.lecture.Instructors as DomainInstructors

fun SearchProfessorResponse.Instructors.toDomainInstructor() = DomainInstructors(
    id = id,
    name = name,
    organization = organization,
    authority = authority,
)

fun SearchProfessorResponse.toEntity() = SearchProfessorEntity(
    instructors = instructors.map { it.toDomainInstructor() }
)