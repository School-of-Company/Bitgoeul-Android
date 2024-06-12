package com.msg.model.entity.certification

import java.time.LocalDate
import java.util.UUID

data class CertificationListEntity(
    val certificationId: UUID,
    val name: String,
    val acquisitionDate: LocalDate,
)
