package com.msg.domain.usecase.faq

import com.msg.data.repository.faq.FaqRepository
import com.msg.model.param.faq.AddFrequentlyAskedQuestionsParam
import javax.inject.Inject

class AddFrequentlyAskedQuestionUseCase @Inject constructor(
    private val faqRepository: FaqRepository
){
    suspend operator fun invoke(body: AddFrequentlyAskedQuestionsParam) = runCatching {
        faqRepository.addFrequentlyAskedQuestions(body = body)
    }
}