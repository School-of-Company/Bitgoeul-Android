package com.msg.network.request.admin

data class GetUserListRequest(
    val keyword: String,
    val page: Int,
    val size: Int,
    val sort: String,
)
