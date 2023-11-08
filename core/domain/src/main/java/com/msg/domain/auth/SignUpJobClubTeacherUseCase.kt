package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import com.msg.model.remote.request.auth.SignUpJobClubTeacherRequest
import javax.inject.Inject

class SignUpJobClubTeacherUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpJobClubTeacherRequest) = runCatching {
        authRepository.signUpJobClubTeacher(body = body)
    }
}