package com.msg.model.remote.request.certification

import java.time.LocalDate

data class WriteCertificationRequest(
    val name: String,
    val acquisitionDate: LocalDate
)
