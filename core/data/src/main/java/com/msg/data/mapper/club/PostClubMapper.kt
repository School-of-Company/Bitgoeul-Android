package com.msg.data.mapper.club

import com.msg.model.param.club.PostClubParam
import com.msg.network.request.club.PostClubRequest

fun PostClubParam.toRequest() = PostClubRequest(
    clubName = clubName,
    field = field
)