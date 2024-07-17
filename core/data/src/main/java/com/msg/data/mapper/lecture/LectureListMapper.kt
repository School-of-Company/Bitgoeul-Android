package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.LectureListEntity
import com.msg.network.response.lecture.LectureListResponse
import com.msg.model.entity.lecture.LectureListEntity.Lectures.ContentArray as DomainContentArray
import com.msg.model.entity.lecture.LectureListEntity.Lectures as DomainLectures
import com.msg.model.entity.lecture.LectureListEntity.Lectures.Pageable as DomainPageable
import com.msg.model.entity.lecture.LectureListEntity.Lectures.Sort as DomainSort

fun LectureListResponse.toEntity() = LectureListEntity(
    lectures = lectures.toDomainLectures()
)

fun LectureListResponse.Lectures.toDomainLectures() = DomainLectures(
    content = content.map { it.toDomainContentArray() },
    pageable = pageable.toDomainPageable(),
    last = last,
    totalPages = totalPages,
    totalElements = totalElements,
    size = size,
    number = number,
    first = first,
    sort = sort.toDomainSort(),
    numberOfElements = numberOfElements,
    empty = empty
)

fun LectureListResponse.Lectures.ContentArray.toDomainContentArray() = DomainContentArray(
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

fun LectureListResponse.Lectures.Pageable.toDomainPageable() = DomainPageable(
    sort = sort.toDomainSort(),
    offset = offset,
    pageNumber = pageNumber,
    pageSize = pageSize,
    paged = paged,
    unpaged = unpaged
)

fun LectureListResponse.Lectures.Sort.toDomainSort() = DomainSort(
    empty = empty,
    sorted = sorted,
    unsorted = unsorted
)