package com.msg.model.remote.response.certification

import java.time.LocalDate
import java.util.UUID

data class DetailCertificationResponse(
    val certificationId: UUID,
    val name: String,
    val acquisitionDate: LocalDate,
)
