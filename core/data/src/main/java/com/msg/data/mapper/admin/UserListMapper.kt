package com.msg.data.mapper.admin

import com.msg.model.entity.admin.UserListEntity
import com.msg.network.response.admin.UserListResponse

fun UserListResponse.toEntity(): UserListEntity {
    return UserListEntity(
        content = content.map { it.toEntity() },
        pageable = pageable.toEntity(),
        totalPages = totalPages,
        totalElement = totalElement,
        last = last,
        numOfElements = numOfElements,
        number = number,
        sort = sort.toEntity(),
        first = first,
        size = size,
        empty = empty,
    )
}

fun UserListResponse.Pageable.toEntity(): UserListEntity.Pageable {
    return UserListEntity.Pageable(
        sort = sort.toEntity(),
        pageSize = pageSize,
        pageNumber = pageNumber,
        offset = offset,
        paged = paged,
        unpaged = unpaged,
    )
}

fun UserListResponse.Sort.toEntity(): UserListEntity.Sort {
    return UserListEntity.Sort(
        unsorted = unsorted,
        sorted = sorted,
        empty = empty,
    )
}