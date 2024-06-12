package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import javax.inject.Inject

class GetMyClubDetailUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke() = runCatching {
        clubRepository.getMyClubDetail()
    }
}