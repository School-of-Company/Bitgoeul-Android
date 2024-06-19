package com.msg.network.request.admin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUserListRequest(
    @Json(name = "keyword") val keyword: String,
    @Json(name = "page") val page: Int,
    @Json(name = "size") val size: Int,
    @Json(name = "sort") val sort: String,
)
