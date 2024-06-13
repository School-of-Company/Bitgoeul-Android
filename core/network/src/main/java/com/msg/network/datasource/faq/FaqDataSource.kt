package com.msg.network.datasource.faq

import com.msg.network.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.network.response.faq.GetFrequentlyAskedQuestionDetailResponse
import kotlinx.coroutines.flow.Flow

interface FaqDataSource {
    fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit>
    fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>>
}