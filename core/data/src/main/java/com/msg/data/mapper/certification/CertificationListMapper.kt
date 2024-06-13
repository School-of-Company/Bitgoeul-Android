package com.msg.data.mapper.certification

import com.msg.model.entity.certification.CertificationListEntity
import com.msg.network.response.certification.CertificationListResponse

fun CertificationListResponse.toEntity() = CertificationListEntity(
    certificationId = certificationId,
    name = name,
    acquisitionDate = acquisitionDate
)