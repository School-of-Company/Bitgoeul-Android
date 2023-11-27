package com.msg.domain.faq

import com.msg.data.repository.faq.FaqRepository
import java.util.UUID
import javax.inject.Inject

class GetFrequentlyAskedQuestionsDetailUseCase @Inject constructor(
    private val faqRepository: FaqRepository
){
    suspend operator fun invoke(id: UUID) = runCatching {
        faqRepository.getFrequentlyAskedQuestionsDetail(id = id)
    }
}