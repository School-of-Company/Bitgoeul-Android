package com.msg.data.repository.certification

import com.msg.model.entity.certification.CertificationListEntity
import com.msg.model.param.certification.WriteCertificationParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationRepository {
    fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListEntity>>
    fun getCertificationListForStudent(): Flow<List<CertificationListEntity>>
    fun writeCertification(body: WriteCertificationParam): Flow<Unit>
    fun editCertification(id: UUID, body: WriteCertificationParam): Flow<Unit>
}