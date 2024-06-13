package com.msg.domain.usecase.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.param.auth.FindPasswordParam
import javax.inject.Inject

class FindPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(body: FindPasswordParam) = runCatching {
        authRepository.findPassword(body = body)
    }
}