package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ActivityDataSource {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit>
}