package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpProfessorParam
import javax.inject.Inject

class SignUpProfessorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpProfessorParam) = runCatching {
        authRepository.signUpProfessor(body = body)
    }
}