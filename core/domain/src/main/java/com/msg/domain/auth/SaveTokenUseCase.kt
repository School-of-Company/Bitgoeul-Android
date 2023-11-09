package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.model.AuthTokenModel
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(data: AuthTokenModel) = runCatching {
        authRepository.saveToken(data)
    }
}