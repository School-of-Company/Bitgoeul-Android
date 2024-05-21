package com.msg.network.datasource.certification

import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationDataSource {
    suspend fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>>
    suspend fun getCertificationListForStudent(): Flow<List<CertificationListResponse>>
    suspend fun writeCertification(body: WriteCertificationRequest): Flow<Unit>
    suspend fun editCertification(id: UUID, body: WriteCertificationRequest): Flow<Unit>
}