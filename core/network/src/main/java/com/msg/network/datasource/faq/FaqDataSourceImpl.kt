package com.msg.network.datasource.faq

import com.msg.network.api.FaqAPI
import com.msg.network.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.network.response.faq.GetFrequentlyAskedQuestionDetailResponse
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FaqDataSourceImpl @Inject constructor(
    private val faqAPI: FaqAPI,
) : FaqDataSource {
    override fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit> =
        flow {
            emit(
                BitgoeulApiHandler<Unit>()
                    .httpRequest { faqAPI.addFrequentlyAskedQuestions(body = body) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>> =
        flow {
            emit(
                BitgoeulApiHandler<List<GetFrequentlyAskedQuestionDetailResponse>>()
                    .httpRequest { faqAPI.getFrequentlyAskedQuestionsList() }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
}