package com.msg.domain.usecase.certification

import com.msg.data.repository.certification.CertificationRepository
import com.msg.model.param.certification.WriteCertificationParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class EditCertificationUseCase @Inject constructor(
    private val certificationRepository: CertificationRepository
) {
    operator fun invoke(id: UUID, body: WriteCertificationParam): Flow<Unit> =
        certificationRepository.editCertification(id = id, body = body)
}