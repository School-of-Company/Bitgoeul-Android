package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import com.msg.model.entity.club.StudentBelongClubEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GetStudentBelongClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    operator fun invoke(id: Long, studentId: UUID): Flow<StudentBelongClubEntity> =
        clubRepository.getStudentBelongClubDetail(id = id, studentId = studentId)
}