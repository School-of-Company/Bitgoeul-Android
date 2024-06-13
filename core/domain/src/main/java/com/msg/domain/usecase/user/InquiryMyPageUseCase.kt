package com.msg.domain.usecase.user

import com.msg.data.repository.user.UserRepository
import javax.inject.Inject

class InquiryMyPageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = runCatching {
        userRepository.getMyPage()
    }
}