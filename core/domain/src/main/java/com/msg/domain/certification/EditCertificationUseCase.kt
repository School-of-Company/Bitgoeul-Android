package com.msg.domain.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.remote.request.certification.WriteCertificationRequest
import java.util.UUID
import javax.inject.Inject

class EditCertificationUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(id: UUID, body: WriteCertificationRequest) = runCatching {
        certificationRepository.editCertification(id = id, body = body)
    }
}