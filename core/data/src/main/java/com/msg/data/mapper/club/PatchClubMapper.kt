package com.msg.data.mapper.club

import com.msg.model.param.club.PatchClubParam
import com.msg.network.request.club.PatchClubRequest

fun PatchClubParam.toRequest() = PatchClubRequest(
    clubName = clubName,
    field = field,
    schoolId = schoolId
)