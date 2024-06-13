package com.msg.network.datasource.certification

import com.msg.network.api.CertificationAPI
import com.msg.network.request.certification.WriteCertificationRequest
import com.msg.network.response.certification.CertificationListResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class CertificationDataSourceImpl @Inject constructor(
    private val certificationAPI: CertificationAPI,
) : CertificationDataSource {
    override fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>> =
        makeRequest { certificationAPI.getCertificationListForTeacher(studentId = studentId) }

    override fun getCertificationListForStudent(): Flow<List<CertificationListResponse>> =
        makeRequest { certificationAPI.getCertificationListForStudent() }

    override fun writeCertification(body: WriteCertificationRequest): Flow<Unit> =
        makeRequest { certificationAPI.writeCertification(body = body) }

    override fun editCertification(id: UUID, body: WriteCertificationRequest): Flow<Unit> =
        makeRequest { certificationAPI.editCertification(id = id, body = body) }
}