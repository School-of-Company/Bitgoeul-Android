package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.request.auth.FindPasswordRequest
import javax.inject.Inject

class FindPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(body: FindPasswordRequest) = runCatching {
        authRepository.findPassword(body = body)
    }
}