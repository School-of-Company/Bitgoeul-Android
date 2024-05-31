package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID


interface ActivityRepository {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit>
    suspend fun getMyStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListResponse>
    suspend fun getStudentActivityInfoList(page: Int, size: Int, id: UUID): Flow<GetStudentActivityListResponse>
    suspend fun getEntireStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListResponse>
    suspend fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoResponse>
}