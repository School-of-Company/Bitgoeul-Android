package com.msg.data.mapper.certification

import com.msg.model.param.certification.WriteCertificationParam
import com.msg.network.request.certification.WriteCertificationRequest

fun WriteCertificationParam.toRequest() = WriteCertificationRequest(
    name = name,
    acquisitionDate = acquisitionDate,
)