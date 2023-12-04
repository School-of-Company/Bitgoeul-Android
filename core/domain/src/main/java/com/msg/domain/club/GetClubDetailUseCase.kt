package com.msg.domain.club

import com.msg.data.repository.club.ClubRepository
import javax.inject.Inject

class GetClubDetailUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(id: Long) = runCatching {
        clubRepository.getClubDetail(id = id)
    }
}