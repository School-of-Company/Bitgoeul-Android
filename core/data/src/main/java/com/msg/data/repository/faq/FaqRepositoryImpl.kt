package com.msg.data.repository.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.FrequentlyAskedQuestionsListResponse
import com.msg.network.datasource.faq.FaqDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FaqRepositoryImpl @Inject constructor(
    private val faqDataSource: FaqDataSource,
) : FaqRepository {
    override suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit> {
        return faqDataSource.addFrequentlyAskedQuestions(
            body = body
        )
    }

    override suspend fun getFrequentlyAskedQuestionsList(): Flow<List<FrequentlyAskedQuestionsListResponse>> {
        return faqDataSource.getFrequentlyAskedQuestionsList()
    }
}