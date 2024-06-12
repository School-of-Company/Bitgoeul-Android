package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpBbozzakTeacherParam
import javax.inject.Inject

class SignUpBbozzakTeacherUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpBbozzakTeacherParam) = runCatching {
        authRepository.signUpBbozzakTeacher(body = body)
    }
}