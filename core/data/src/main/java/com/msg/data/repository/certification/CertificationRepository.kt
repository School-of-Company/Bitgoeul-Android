package com.msg.data.repository.certification

import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationRepository {
    suspend fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>>
    suspend fun getCertificationListForStudent(): Flow<List<CertificationListResponse>>
    suspend fun wrtieCertification(body: WriteCertificationRequest): Flow<Unit>
}