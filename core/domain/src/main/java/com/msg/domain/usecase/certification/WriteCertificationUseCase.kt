package com.msg.domain.usecase.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.param.certification.WriteCertificationParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WriteCertificationUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    operator fun invoke(body: WriteCertificationParam): Flow<Unit> =
        certificationRepository.writeCertification(body = body)
}