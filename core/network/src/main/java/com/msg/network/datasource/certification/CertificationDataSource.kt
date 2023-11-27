package com.msg.network.datasource.certification

import com.msg.model.remote.response.lecture.DetailLectureResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationDataSource {
    suspend fun getCertificationList(studentId: UUID): Flow<List<DetailLectureResponse>>
}