package com.msg.domain.activity

import com.msg.data.repository.user.UserRepository
import com.msg.model.remote.request.user.ChangePasswordRequest
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(body: ChangePasswordRequest) = runCatching {
        userRepository.changePassword(body = body)
    }
}