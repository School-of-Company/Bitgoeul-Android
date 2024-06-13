package com.msg.data.mapper.club

import com.msg.model.entity.club.ClubDetailEntity
import com.msg.network.response.club.ClubDetailResponse
import com.msg.model.entity.club.ClubDetailEntity.Student as DomainStudent
import com.msg.model.entity.club.ClubDetailEntity.Teacher as DomainTeacher

fun ClubDetailResponse.toEntity() = ClubDetailEntity(
    clubId = clubId,
    clubName = clubName,
    highSchoolName = highSchoolName,
    headCount = headCount,
    students = students.map { it.toDomainStudent() },
    teacher = teacher.toDomainTeacher()
)

fun ClubDetailResponse.Student.toDomainStudent() = DomainStudent(
    id = id,
    name = name
)

fun ClubDetailResponse.Teacher.toDomainTeacher() = DomainTeacher(
    id = id,
    name = name
)