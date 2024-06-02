package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ActivityDataSource {
    fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    fun approveStudentActivityInfo(id: UUID): Flow<Unit>
    fun rejectStudentActivityInfo(id: UUID): Flow<Unit>
    fun deleteStudentActivityInfo(id: UUID): Flow<Unit>
    fun getMyStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListResponse>
    fun getStudentActivityInfoList(page: Int, size: Int, id: UUID): Flow<GetStudentActivityListResponse>
    fun getEntireStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListResponse>
    fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoResponse>
}