package com.msg.data.mapper.government

import com.msg.model.param.government.PostGovernmentParam
import com.msg.network.request.government.PostGovernmentRequest

fun PostGovernmentParam.toRequest() = PostGovernmentRequest(
    field = field,
    governmentName = governmentName
)