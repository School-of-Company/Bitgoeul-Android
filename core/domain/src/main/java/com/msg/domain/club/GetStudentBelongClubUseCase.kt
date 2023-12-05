package com.msg.domain.club

import com.msg.data.repository.club.ClubRepository
import javax.inject.Inject

class GetStudentBelongClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(id: Long) = runCatching {
        clubRepository.getStudentBelongClubList(id = id)
    }
}