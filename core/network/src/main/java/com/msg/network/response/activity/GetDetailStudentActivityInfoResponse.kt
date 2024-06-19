package com.msg.network.response.activity

import com.msg.model.enumdata.ApproveStatus
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@JsonClass(generateAdapter = true)
data class GetDetailStudentActivityInfoResponse(
    @Json(name = "id") val id: UUID,
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "credit") val credit: Int,
    @Json(name = "activityDate") val activityDate: LocalDate,
    @Json(name = "modifiedAt") val modifiedAt: LocalDateTime,
    @Json(name = "approveState") val approveState: ApproveStatus
)
