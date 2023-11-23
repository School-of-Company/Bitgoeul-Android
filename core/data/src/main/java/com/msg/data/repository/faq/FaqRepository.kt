package com.msg.data.repository.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest

interface FaqRepository {
    suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest)
}