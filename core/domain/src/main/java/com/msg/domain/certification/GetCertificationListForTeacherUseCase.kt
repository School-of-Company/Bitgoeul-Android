package com.msg.domain.lecture

import com.msg.data.repository.certification.CertificationRepository
import java.util.UUID
import javax.inject.Inject

class GetCertificationListForTeacherUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository,
) {
    suspend operator fun invoke(studentId: UUID) = runCatching {
        certificationRepository.getCertificationListForTeacher(studentId = studentId)
    }
}