package com.msg.domain.auth

import com.msg.data.repository.AuthRepository
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = runCatching {
        authRepository.withdraw()
    }
}