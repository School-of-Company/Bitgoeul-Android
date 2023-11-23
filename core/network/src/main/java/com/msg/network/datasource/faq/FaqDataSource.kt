package com.msg.network.datasource.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import kotlinx.coroutines.flow.Flow

interface FaqDataSource {
    suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit>
}