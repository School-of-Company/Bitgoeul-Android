package com.msg.network.response.admin

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResponse(
    val content: List<UserListContentResponse>,
    val pageable: Pageable,
    val totalPages: Int,
    val totalElement: Int,
    val last: Boolean,
    val numOfElements: Int,
    val number: Int,
    val sort: Sort,
    val first: Boolean,
    val size: Int,
    val empty: Boolean,
) {
    data class Pageable(
        val sort: Sort,
        val pageSize: Int,
        val pageNumber: Int,
        val offset: Int,
        val paged: Boolean,
        val unpaged: Boolean,
    )

    data class Sort(
        val unsorted: Boolean,
        val sorted: Boolean,
        val empty: Boolean,
    )
}
