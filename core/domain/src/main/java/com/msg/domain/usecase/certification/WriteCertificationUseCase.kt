package com.msg.domain.usecase.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.param.certification.WriteCertificationParam
import javax.inject.Inject

class WriteCertificationUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(body: WriteCertificationParam) = runCatching {
        certificationRepository.writeCertification(body = body)
    }
}