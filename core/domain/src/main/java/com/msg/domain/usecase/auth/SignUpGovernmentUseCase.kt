package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpGovernmentParam
import javax.inject.Inject

class SignUpGovernmentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpGovernmentParam) = runCatching {
        authRepository.signUpGovernment(body = body)
    }
}
