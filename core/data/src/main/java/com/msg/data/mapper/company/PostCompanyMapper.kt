package com.msg.data.mapper.company

import com.msg.model.param.company.PostCompanyParam
import com.msg.network.request.company.PostCompanyRequest

fun PostCompanyParam.toRequest() = PostCompanyRequest(
    companyName = companyName,
    field = field
)