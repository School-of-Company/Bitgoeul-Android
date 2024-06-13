package com.msg.domain.usecase.activity

import com.msg.data.repository.user.UserRepository
import com.msg.model.param.user.ChangePasswordParam
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(body: ChangePasswordParam) = runCatching {
        userRepository.changePassword(body = body)
    }
}