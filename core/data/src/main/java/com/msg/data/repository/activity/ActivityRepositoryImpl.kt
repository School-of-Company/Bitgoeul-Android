package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.network.datasource.activity.ActivityDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityDataSource: ActivityDataSource
) : ActivityRepository {
    override suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.addStudentActivityInfo(
            body = body
        )
    }

}