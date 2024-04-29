package com.msg.network.datasource.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse
import kotlinx.coroutines.flow.Flow

interface FaqDataSource {
    suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit>
    suspend fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>>
}