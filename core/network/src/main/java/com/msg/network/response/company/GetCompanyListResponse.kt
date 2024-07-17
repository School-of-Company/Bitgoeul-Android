package com.msg.network.response.company

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCompanyListResponse(
    @Json(name = "companies") val companies: List<Company>
) {
    data class Company(
        @Json(name = "id") val id: Long,
        @Json(name = "companyName") val companyName: String,
        @Json(name = "field") val field: String
    )
}