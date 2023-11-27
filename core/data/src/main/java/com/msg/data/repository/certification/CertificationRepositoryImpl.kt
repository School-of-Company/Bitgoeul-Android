package com.msg.data.repository.certification

import com.msg.model.remote.response.certification.DetailCertificationResponse
import com.msg.network.datasource.certification.CertificationDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val certificationDataSource: CertificationDataSource
) : CertificationRepository {
    override suspend fun getDetailCertificationList(studentId: UUID): Flow<List<DetailCertificationResponse>> {
        return certificationDataSource.getCertificationList(studentId = studentId)
    }

}