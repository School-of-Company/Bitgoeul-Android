package com.msg.data.mapper.admin

import com.msg.model.param.admin.GetUserListParam
import com.msg.network.request.admin.GetUserListRequest

fun GetUserListParam.toRequest() = GetUserListRequest(
    keyword = keyword,
    page = page,
    size = size,
    sort = sort,
)