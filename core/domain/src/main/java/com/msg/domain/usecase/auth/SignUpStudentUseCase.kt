package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpStudentParam
import javax.inject.Inject

class SignUpStudentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpStudentParam) = runCatching {
        authRepository.signUpStudent(body = body)
    }
}