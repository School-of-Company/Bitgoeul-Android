package com.msg.network.response.activity

import com.msg.model.model.activity.GetStudentActivityModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetStudentActivityListResponse(
    @Json(name = "content") val content: List<GetStudentActivityModel>,
    @Json(name = "pageable") val pageable: Pageable,
    @Json(name = "totalPages") val totalPages: Int,
    @Json(name = "totalElements") val totalElements: Int,
    @Json(name = "last") val last: Boolean,
    @Json(name = "numberOfElements") val numberOfElements: Int,
    @Json(name = "number") val number: Int,
    @Json(name = "sort") val sort: Sort,
    @Json(name = "first") val first: Boolean,
    @Json(name = "size") val size: Int,
    @Json(name = "empty") val empty: Boolean,
) {
    data class Pageable(
        @Json(name = "sort") val sort: Sort,
        @Json(name = "pageSize") val pageSize: Int,
        @Json(name = "pageNumber") val pageNumber: Int,
        @Json(name = "offset") val offset: Int,
        @Json(name = "paged") val paged: Boolean,
        @Json(name = "unpaged") val unpaged: Boolean,
    )

    data class Sort(
        @Json(name = "unsorted") val unsorted: Boolean,
        @Json(name = "sorted") val sorted: Boolean,
        @Json(name = "empty") val empty: Boolean,
    )
}