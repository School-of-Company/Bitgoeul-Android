package com.msg.domain.usecase.email

import com.msg.data.repository.email.EmailRepository
import javax.inject.Inject

class GetEmailAuthenticateStatusUseCase @Inject constructor(
    private val emailRepository: EmailRepository
) {
    suspend operator fun invoke(email: String) = runCatching {
        emailRepository.getEmailAuthenticateStatus(email = email)
    }
}