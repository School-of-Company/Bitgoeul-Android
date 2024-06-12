package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.entity.auth.AuthTokenEntity
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(data: AuthTokenEntity) = runCatching {
        authRepository.saveToken(data)
    }
}