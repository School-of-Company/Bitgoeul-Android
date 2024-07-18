package com.msg.data.mapper.university

import com.msg.model.entity.university.GetUniversityEntity
import com.msg.network.response.university.GetUniversityResponse
import com.msg.model.entity.university.GetUniversityEntity.University as DomainUniversity
import com.msg.network.response.university.GetUniversityResponse.University

fun GetUniversityResponse.toEntity() = GetUniversityEntity(
    universities = universities.map { it.toDomainUniversity() }
)

fun University.toDomainUniversity() = DomainUniversity(
    id = id,
    universityName = universityName,
    departments = departments
)