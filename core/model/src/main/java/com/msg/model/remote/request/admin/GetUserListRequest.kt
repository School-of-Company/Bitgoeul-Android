package com.msg.model.remote.request.admin

data class GetUserListRequest(
    val keyword: String,
    val page: Int,
    val size: Int,
    val sort: String,
)
