package com.msg.network.response.faq

data class GetFrequentlyAskedQuestionDetailResponse(
    val id : Long,
    val question : String,
    val answer : String,
)
