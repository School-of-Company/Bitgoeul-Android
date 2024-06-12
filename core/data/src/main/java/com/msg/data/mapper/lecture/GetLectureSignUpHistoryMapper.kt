package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.network.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.model.entity.lecture.SignUpLectures as DomainSignUpLectures

fun GetLectureSignUpHistoryResponse.SignUpLectures.toDomainSignUpLecture() = DomainSignUpLectures(
    id = id,
    name = name,
    lectureType = lectureType,
    currentCompletedDate = currentCompletedDate,
    lecturer = lecturer,
    isComplete = isComplete,
)

fun GetLectureSignUpHistoryResponse.toEntity() = GetLectureSignUpHistoryEntity(
    lectures = lectures.map { it.toDomainSignUpLecture() }
)