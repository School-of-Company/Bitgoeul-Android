package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import kotlinx.coroutines.flow.Flow


interface ActivityRepository {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
}