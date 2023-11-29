package com.msg.network.datasource.certification

import com.msg.model.remote.response.certification.CertificationListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationDataSource {
    suspend fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>>
    suspend fun getCertificationListForStudent(): Flow<List<CertificationListResponse>>

}