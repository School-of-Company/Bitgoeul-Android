package com.msg.data.mapper.faq

import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity
import com.msg.network.response.faq.GetFrequentlyAskedQuestionDetailResponse

fun GetFrequentlyAskedQuestionDetailResponse.toEntity() = GetFrequentlyAskedQuestionDetailEntity(
    id = id,
    question = question,
    answer = answer
)