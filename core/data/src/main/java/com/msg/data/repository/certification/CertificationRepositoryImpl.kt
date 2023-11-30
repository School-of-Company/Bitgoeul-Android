package com.msg.data.repository.certification

import com.msg.model.remote.response.certification.CertificationListResponse
import com.msg.network.datasource.certification.CertificationDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val certificationDataSource: CertificationDataSource
) : CertificationRepository {
    override suspend fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForTeacher(studentId = studentId)
    }

    override suspend fun getCertificationListForStudent(): Flow<List<CertificationListResponse>> {
        return certificationDataSource.getCertificationListForStudent()
    }

}