package com.msg.data.repository.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.FrequentlyAskedQuestionsListResponse
import kotlinx.coroutines.flow.Flow

interface FaqRepository {
    suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit>
    suspend fun getFrequentlyAskedQuestionsList(): Flow<List<FrequentlyAskedQuestionsListResponse>>
}