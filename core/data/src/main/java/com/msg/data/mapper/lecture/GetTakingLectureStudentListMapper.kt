package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.GetTakingLectureStudentListEntity
import com.msg.network.response.lecture.GetTakingLectureStudentListResponse
import com.msg.model.entity.lecture.Students as DomainStudents

fun GetTakingLectureStudentListResponse.Students.toDomainStudents() = DomainStudents(
    id = id,
    email = email,
    name = name,
    grade = grade,
    classNumber = classNumber,
    number = number,
    phoneNumber = phoneNumber,
    school = school,
    clubName = clubName,
    cohort = cohort,
    isCompleted = isCompleted,
)

fun GetTakingLectureStudentListResponse.toEntity() =
    GetTakingLectureStudentListEntity(
        students = students.map { it.toDomainStudents() }
    )