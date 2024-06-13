package com.msg.domain.usecase.certification

import com.msg.data.repository.certification.CertificationRepository
import javax.inject.Inject

class GetCertificationListForStudentUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository,
) {
    suspend operator fun invoke() = runCatching {
        certificationRepository.getCertificationListForStudent()
    }
}