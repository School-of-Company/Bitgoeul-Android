package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import com.msg.model.remote.request.SignUpStudentRequest
import javax.inject.Inject

class SignUpStudentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpStudentRequest) = runCatching {
        authRepository.signUpStudent(body = body)
    }
}