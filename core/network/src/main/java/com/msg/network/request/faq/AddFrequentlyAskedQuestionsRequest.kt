package com.msg.network.request.faq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddFrequentlyAskedQuestionsRequest(
    @Json(name = "question") val question: String,
    @Json(name = "answer") val answer: String,
)
