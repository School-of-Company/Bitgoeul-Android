package com.msg.network.datasource.faq

import com.msg.network.api.FaqAPI
import com.msg.network.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.network.response.faq.GetFrequentlyAskedQuestionDetailResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FaqDataSourceImpl @Inject constructor(
    private val faqAPI: FaqAPI,
) : FaqDataSource {
    override fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit> =
        makeRequest { faqAPI.addFrequentlyAskedQuestions(body = body) }

    override fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>> =
        makeRequest { faqAPI.getFrequentlyAskedQuestionsList() }
}