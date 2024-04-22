package com.msg.network.datasource.faq

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.FrequentlyAskedQuestionsListResponse
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse
import com.msg.network.api.FaqAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class FaqDataSourceImpl @Inject constructor(
    private val faqAPI: FaqAPI,
) : FaqDataSource {
    override suspend fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsRequest): Flow<Unit> =
        flow {
            emit(
                BitgoeulApiHandler<Unit>()
                    .httpRequest { faqAPI.addFrequentlyAskedQuestions(body = body) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailResponse>> =
        flow {
            emit(
                BitgoeulApiHandler<List<GetFrequentlyAskedQuestionDetailResponse>>()
                    .httpRequest { faqAPI.getFrequentlyAskedQuestionsList() }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
}