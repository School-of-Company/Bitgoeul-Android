package com.msg.model.remote.response.faq

data class GetFrequentlyAskedQuestionDetailResponse(
    val id : Long,
    val question : String,
    val answer : String,
)
