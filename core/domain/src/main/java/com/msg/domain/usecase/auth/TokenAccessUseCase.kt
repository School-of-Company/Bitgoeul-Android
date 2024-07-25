package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import javax.inject.Inject

class TokenAccessUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = runCatching {
        authRepository.tokenAccess()
    }
}