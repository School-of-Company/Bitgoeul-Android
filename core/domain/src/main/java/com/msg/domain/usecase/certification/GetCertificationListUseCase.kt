package com.msg.domain.usecase.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.entity.certification.CertificationListEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GetCertificationListUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository,
) {
    operator fun invoke(role: String, studentId: UUID): Flow<List<CertificationListEntity>> {
        return when (role) {
            "ROLE_STUDENT" -> certificationRepository.getCertificationListForStudent()
            else -> certificationRepository.getCertificationListForTeacher(studentId = studentId)
        }
    }
}