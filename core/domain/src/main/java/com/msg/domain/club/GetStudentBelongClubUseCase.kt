package com.msg.domain.club

import com.msg.data.repository.club.ClubRepository
import java.util.UUID
import javax.inject.Inject

class GetStudentBelongClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(id: Long, studentId: UUID) = runCatching {
        clubRepository.getStudentBelongClubDetail(id = id, studentId = studentId)
    }
}