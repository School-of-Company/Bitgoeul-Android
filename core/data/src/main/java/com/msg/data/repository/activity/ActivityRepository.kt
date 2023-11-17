package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID


interface ActivityRepository {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun inquiryMyStudentActivityInfo(page: Int, size: Int, sort: String): Flow<InquiryStudentActivityListResponse>
}