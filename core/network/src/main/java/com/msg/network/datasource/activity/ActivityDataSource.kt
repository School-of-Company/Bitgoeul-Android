package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import kotlinx.coroutines.flow.Flow

interface ActivityDataSource {
    suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
}