package com.msg.network.response.certification

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.util.UUID
@JsonClass(generateAdapter = true)
data class CertificationListResponse(
    @Json(name = "certificationId") val certificationId: UUID,
    @Json(name = "name") val name: String,
    @Json(name = "acquisitionDate") val acquisitionDate: LocalDate,
)
