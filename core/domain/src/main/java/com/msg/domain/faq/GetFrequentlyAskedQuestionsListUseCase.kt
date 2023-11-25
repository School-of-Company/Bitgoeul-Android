package com.msg.domain.faq

import com.msg.data.repository.faq.FaqRepository
import javax.inject.Inject

class GetFrequentlyAskedQuestionsListUseCase @Inject constructor(
    private val faqRepository: FaqRepository
) {
    suspend operator fun invoke() = runCatching {
        faqRepository.getFrequentlyAskedQuestionsList()
    }
}