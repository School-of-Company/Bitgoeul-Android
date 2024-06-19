package com.msg.network.request.certification

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class WriteCertificationRequest(
    @Json(name = "name") val name: String,
    @Json(name = "acquisitionDate") val acquisitionDate: LocalDate
)
