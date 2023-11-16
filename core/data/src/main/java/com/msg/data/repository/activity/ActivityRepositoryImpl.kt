package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.network.datasource.activity.ActivityDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityDataSource: ActivityDataSource
) : ActivityRepository {
    override suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.addStudentActivityInfo(
            body = body
        )
    }

    override suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.editStudentActivityInfo(
            id = id,
            body = body
        )
    }

    override suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.approveStudentActivityInfo(
            id = id
        )
    }
}