package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.request.auth.SignUpProfessorRequest
import javax.inject.Inject

class SignUpProfessorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpProfessorRequest) = runCatching {
        authRepository.signUpProfessor(body = body)
    }
}