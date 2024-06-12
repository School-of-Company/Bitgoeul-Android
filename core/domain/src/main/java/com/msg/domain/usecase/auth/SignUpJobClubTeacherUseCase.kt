package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.SignUpJobClubTeacherParam
import javax.inject.Inject

class SignUpJobClubTeacherUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpJobClubTeacherParam) = runCatching {
        authRepository.signUpJobClubTeacher(body = body)
    }
}