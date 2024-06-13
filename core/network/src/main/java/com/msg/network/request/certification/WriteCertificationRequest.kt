package com.msg.network.request.certification

import java.time.LocalDate

data class WriteCertificationRequest(
    val name: String,
    val acquisitionDate: LocalDate
)
