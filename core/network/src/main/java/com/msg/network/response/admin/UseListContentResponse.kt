package com.msg.network.response.admin

import com.msg.model.enumdata.ApproveStatus
import com.msg.model.enumdata.Authority
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class UserListContentResponse(
    @Json(name = "id") val id: UUID,
    @Json(name = "name") val name: String,
    @Json(name = "authority") val authority: Authority,
    @Json(name = "approveStatus") val approveStatus: ApproveStatus
)