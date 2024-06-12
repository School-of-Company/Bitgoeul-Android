package com.msg.data.repository.faq

import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity
import com.msg.model.param.faq.AddFrequentlyAskedQuestionsParam
import kotlinx.coroutines.flow.Flow

interface FaqRepository {
    fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsParam): Flow<Unit>
    fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailEntity>>
}