package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
import com.msg.network.datasource.activity.ActivityDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityDataSource: ActivityDataSource
) : ActivityRepository {
    override fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.addStudentActivityInfo(
            body = body
        )
    }

    override fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.editStudentActivityInfo(
            id = id,
            body = body
        )
    }

    override fun approveStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.approveStudentActivityInfo(
            id = id
        )
    }

    override fun rejectStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.rejectStudentActivityInfo(
            id = id
        )
    }

    override fun deleteStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.deleteStudentActivityInfo(
            id = id
        )
    }

    override fun getMyStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListResponse> {
        return activityDataSource.getMyStudentActivityInfoList(
            page = page,
            size = size,
        )
    }

    override fun getStudentActivityInfoList(
        page: Int,
        size: Int,
        id: UUID
    ): Flow<GetStudentActivityListResponse> {
        return activityDataSource.getStudentActivityInfoList(
            page = page,
            size = size,
            id = id
        )
    }

    override fun getEntireStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListResponse> {
        return activityDataSource.getEntireStudentActivityInfoList(
            page = page,
            size = size,
        )
    }

    override fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoResponse> {
        return activityDataSource.getDetailStudentActivityInfo(
            id = id
        )
    }
}