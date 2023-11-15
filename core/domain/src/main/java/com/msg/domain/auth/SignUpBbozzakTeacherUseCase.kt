package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.request.auth.SignUpBbozzakTeacherRequest
import javax.inject.Inject

class SignUpBbozzakTeacherUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpBbozzakTeacherRequest) = runCatching {
        authRepository.signUpBbozzakTeacher(body = body)
    }
}