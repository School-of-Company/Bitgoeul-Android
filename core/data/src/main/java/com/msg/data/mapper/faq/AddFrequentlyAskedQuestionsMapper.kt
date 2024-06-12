package com.msg.data.mapper.faq

import com.msg.model.param.faq.AddFrequentlyAskedQuestionsParam
import com.msg.network.request.faq.AddFrequentlyAskedQuestionsRequest

fun AddFrequentlyAskedQuestionsParam.toRequest() = AddFrequentlyAskedQuestionsRequest(
    question = question,
    answer = answer,
)