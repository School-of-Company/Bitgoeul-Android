package com.msg.data.repository.faq

import com.msg.data.mapper.faq.toEntity
import com.msg.data.mapper.faq.toRequest
import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity
import com.msg.model.param.faq.AddFrequentlyAskedQuestionsParam
import com.msg.network.datasource.faq.FaqDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class FaqRepositoryImpl @Inject constructor(
    private val faqDataSource: FaqDataSource,
) : FaqRepository {
    override fun addFrequentlyAskedQuestions(body: AddFrequentlyAskedQuestionsParam): Flow<Unit> {
        return faqDataSource.addFrequentlyAskedQuestions(
            body = body.toRequest()
        )
    }

    override fun getFrequentlyAskedQuestionsList(): Flow<List<GetFrequentlyAskedQuestionDetailEntity>> {
        return faqDataSource.getFrequentlyAskedQuestionsList()
            .transform { response ->
                emit(response.map { it.toEntity() })
            }
    }
}