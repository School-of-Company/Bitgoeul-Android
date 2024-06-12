package com.msg.domain.usecase.email

import com.msg.data.repository.email.EmailRepository
import com.msg.model.param.email.SendLinkToEmailParam
import javax.inject.Inject

class SendLinkToEmailUseCase @Inject constructor(
    private val emailRepository: EmailRepository
) {
    suspend operator fun invoke(body: SendLinkToEmailParam) = runCatching {
        emailRepository.sendLinkToEmail(body = body)
    }
}
