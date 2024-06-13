package com.msg.data.mapper.activity

import com.msg.model.entity.activity.GetStudentActivityListEntity
import com.msg.network.response.activity.GetStudentActivityListResponse
import com.msg.model.entity.activity.GetStudentActivityListEntity.Pageable as DomainPageable
import com.msg.model.entity.activity.GetStudentActivityListEntity.Sort as DomainSort

fun GetStudentActivityListResponse.toEntity() = GetStudentActivityListEntity(
    content = content,
    pageable = pageable.toDomainPageable(),
    totalPages = totalPages,
    totalElements = totalElements,
    last = last,
    numberOfElements = numberOfElements,
    number = number,
    sort = sort.toDomainSort(),
    first = first,
    size = size,
    empty = empty
)

fun GetStudentActivityListResponse.Pageable.toDomainPageable() = DomainPageable(
    sort = sort.toDomainSort(),
    pageSize = pageSize,
    pageNumber = pageNumber,
    offset = offset,
    paged = paged,
    unpaged = unpaged
)
fun GetStudentActivityListResponse.Sort.toDomainSort() = DomainSort(
    unsorted = unsorted,
    sorted = sorted,
    empty = empty
)