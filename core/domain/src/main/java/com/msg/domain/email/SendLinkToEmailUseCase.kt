package com.msg.domain.email

import com.msg.data.repository.email.EmailRepository
import com.msg.model.remote.request.email.SendLinkToEmailRequest
import javax.inject.Inject

class SendLinkToEmailUseCase @Inject constructor(
    private val emailRepository: EmailRepository
) {
    suspend operator fun invoke(body: SendLinkToEmailRequest) = runCatching {
        emailRepository.sendLinkToEmail(body = body)
    }
}
