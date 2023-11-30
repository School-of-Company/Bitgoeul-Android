package com.msg.domain.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.remote.request.certification.WriteCertificationRequest
import javax.inject.Inject

class WriteCertificationUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(body: WriteCertificationRequest) = runCatching {
        certificationRepository.wrtieCertification(body = body)
    }
}