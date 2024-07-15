package com.msg.data.mapper.lecture

import com.msg.model.param.lecture.OpenLectureParam
import com.msg.network.request.lecture.OpenLectureRequest

fun OpenLectureParam.toRequest() = OpenLectureRequest(
    name = name,
    content = content,
    semester = semester,
    division = division,
    department = department,
    line = line,
    userId = userId,
    startDate = startDate,
    endDate = endDate,
    lectureDates = lectureDates,
    lectureType = lectureType,
    credit = credit,
    address = address,
    locationDetails = locationDetails,
    maxRegisteredUser = maxRegisteredUser,
    essentialComplete = essentialComplete,
)