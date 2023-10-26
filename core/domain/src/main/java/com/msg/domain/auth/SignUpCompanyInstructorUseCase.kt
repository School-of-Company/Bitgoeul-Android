package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import com.msg.model.remote.request.SignUpCompanyInstructorRequest
import javax.inject.Inject

class SignUpCompanyInstructorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpCompanyInstructorRequest) = runCatching {
        authRepository.signUpCompanyInstructor(body = body)
    }
}