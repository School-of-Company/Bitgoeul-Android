package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ActivityDataSource {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun inquiryMyStudentActivityInfoList(page: Int, size: Int, sort: String): Flow<InquiryStudentActivityListResponse>
    suspend fun inquiryStudentActivityInfoList(page: Int, size: Int, sort: String, id: UUID): Flow<InquiryStudentActivityListResponse>
    suspend fun inquiryEntireStudentActivityInfoList(page: Int, size: Int, sort: String): Flow<InquiryStudentActivityListResponse>
    suspend fun inquiryDetailStudentActivityInfo(id: UUID): Flow<InquiryDetailStudentActivityInfoResponse>
}