package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID


interface ActivityRepository {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
}