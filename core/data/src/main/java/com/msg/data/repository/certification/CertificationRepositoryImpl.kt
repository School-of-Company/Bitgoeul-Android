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
    override fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForTeacher(studentId = studentId)
    }

    override fun getCertificationListForStudent(): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForStudent()
    }

    override fun writeCertification(body: WriteCertificationRequest): Flow<Unit> {
        return certificationDataSource.writeCertification(
            body = body
        )
    }

    override fun editCertification(
        id: UUID,
        body: WriteCertificationRequest,
    ): Flow<Unit> {
        return certificationDataSource.editCertification(
            id = id,
            body = body
        )
    }

}