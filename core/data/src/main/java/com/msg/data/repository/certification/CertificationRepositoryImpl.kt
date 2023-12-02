package com.msg.data.repository.certification

import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import com.msg.network.datasource.certification.CertificationDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val certificationDataSource: CertificationDataSource,
) : CertificationRepository {
    override suspend fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForTeacher(studentId = studentId)
    }

    override suspend fun getCertificationListForStudent(): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForStudent()
    }

    override suspend fun writeCertification(body: WriteCertificationRequest): Flow<Unit> {
        return certificationDataSource.writeCertification(
            body = body
        )
    }

    override suspend fun editCertification(
        studentId: UUID,
        id: UUID,
        body: WriteCertificationRequest,
    ): Flow<Unit> {
        return certificationDataSource.editCertification(
            studentId = studentId,
            id = id,
            body = body
        )
    }

}