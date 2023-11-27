package com.msg.data.repository.certification

import com.msg.model.remote.response.certification.DetailCertificationResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationRepository {
    suspend fun getDetailCertificationList(studentId: UUID): Flow<List<DetailCertificationResponse>>
}