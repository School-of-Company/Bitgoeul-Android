package com.msg.network.response.government

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetGovernmentResponse(
    @Json(name = "governments") val governments: List<Government>
) {
    @JsonClass(generateAdapter = true)
    data class Government(
        @Json(name = "id") val id: Long,
        @Json(name = "field") val field: String,
        @Json(name = "governmentName") val governmentName: String
    )
}