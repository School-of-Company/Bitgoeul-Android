package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.DetailLectureEntity
import com.msg.model.model.lecture.LectureDates as DomainLectureDates
import com.msg.network.response.lecture.DetailLectureResponse

fun DetailLectureResponse.toEntity() = DetailLectureEntity(
    name = name,
    content = content,
    semester = semester,
    division = division,
    department = department,
    line = line,
    createAt = createAt,
    startDate = startDate,
    endDate = endDate,
    lectureDates = lectureDates.map { it.toDomainLectureDates() },
    lectureType = lectureType,
    lectureStatus = lectureStatus,
    headCount = headCount,
    maxRegisteredUser = maxRegisteredUser,
    isRegistered = isRegistered,
    lecturer = lecturer,
    credit = credit,
    locationX = locationX,
    locationY = locationY,
    address = address,
    locationDetails = locationDetails,
    essentialComplete = essentialComplete
)

fun DetailLectureResponse.LectureDates.toDomainLectureDates() = DomainLectureDates(
    completeDate = completeDate,
    startTime = startTime,
    endTime = endTime
)