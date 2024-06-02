package com.msg.data.repository.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse
import kotlinx.coroutines.flow.Flow

interface FaqRepository {
    fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit>
    fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>>
}