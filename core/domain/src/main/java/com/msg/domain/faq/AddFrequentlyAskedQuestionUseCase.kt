package com.msg.domain.faq

import com.msg.data.repository.faq.FaqRepository
import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import javax.inject.Inject

class AddFrequentlyAskedQuestionUseCase @Inject constructor(
    private val faqRepository: FaqRepository
){
    suspend operator fun invoke(body: AddFrequentlyAskedQuestionsRequest) = runCatching {
        faqRepository.addFrequentlyAskedQuestions(body = body)
    }
}