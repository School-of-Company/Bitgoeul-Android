package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import com.msg.model.remote.request.auth.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: LoginRequest) = runCatching {
        authRepository.login(body = body)
    }
}