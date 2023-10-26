package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import com.msg.model.remote.request.SignUpProfessorRequest
import javax.inject.Inject

class SignUpProfessorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpProfessorRequest) = runCatching {
        authRepository.signUpProfessor(body = body)
    }
}