package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpCompanyInstructorParam
import javax.inject.Inject

class SignUpCompanyInstructorUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpCompanyInstructorParam) = runCatching {
        authRepository.signUpCompanyInstructor(body = body)
    }
}