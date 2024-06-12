package com.msg.model.param.certification

import java.time.LocalDate

data class WriteCertificationParam(
    val name: String,
    val acquisitionDate: LocalDate
)
