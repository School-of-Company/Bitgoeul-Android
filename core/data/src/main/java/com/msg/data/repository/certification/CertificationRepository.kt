package com.msg.data.repository.certification

import com.msg.model.remote.response.certification.CertificationListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationRepository {
    suspend fun getCertificationList(studentId: UUID): Flow<List<CertificationListResponse>>
}