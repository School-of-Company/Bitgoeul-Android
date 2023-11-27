package com.msg.domain.certification

import com.msg.data.repository.certification.CertificationRepository
import java.util.UUID
import javax.inject.Inject

class GetDetailCertificationListUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(studentId: UUID) = runCatching {
        certificationRepository.getDetailCertificationList(studentId = studentId)
    }
}