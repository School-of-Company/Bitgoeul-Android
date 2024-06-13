package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.LectureListEntity
import com.msg.network.response.lecture.LectureListResponse
import com.msg.model.entity.lecture.ContentArray as DomainContentArray
import com.msg.model.entity.lecture.Lectures as DomainLectures

fun LectureListResponse.toEntity() = LectureListEntity(
    lectures = lectures.toDomainLectures()
)

fun LectureListResponse.Lectures.toDomainLectures() = DomainLectures(
    content = content.map { it.toDomainContentArray() }
)

fun LectureListResponse.ContentArray.toDomainContentArray() = DomainContentArray(
    id = id,
    name = name,
    content = content,
    semester = semester,
    division = division,
    department = department,
    line = line,
    startDate = startDate,
    endDate = endDate,
    lectureType = lectureType,
    lectureStatus = lectureStatus,
    headCount = headCount,
    maxRegisteredUser = maxRegisteredUser,
    lecturer = lecturer,
    essentialComplete = essentialComplete
)