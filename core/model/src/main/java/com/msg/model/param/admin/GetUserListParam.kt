package com.msg.model.param.admin

data class GetUserListParam(
    val keyword: String,
    val page: Int,
    val size: Int,
    val sort: String,
)
