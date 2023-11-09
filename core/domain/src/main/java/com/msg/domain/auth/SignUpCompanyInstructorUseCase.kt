package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.request.auth.SignUpCompanyInstructorRequest
import javax.inject.Inject

class SignUpCompanyInstructorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpCompanyInstructorRequest) = runCatching {
        authRepository.signUpCompanyInstructor(body = body)
    }
}