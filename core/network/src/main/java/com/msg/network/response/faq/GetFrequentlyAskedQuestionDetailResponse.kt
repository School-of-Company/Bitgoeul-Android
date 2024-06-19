package com.msg.network.response.faq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetFrequentlyAskedQuestionDetailResponse(
    @Json(name = "id") val id : Long,
    @Json(name = "question") val question : String,
    @Json(name = "answer") val answer : String,
)
