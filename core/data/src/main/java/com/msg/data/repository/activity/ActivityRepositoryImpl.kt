package com.msg.data.repository.activity

import com.msg.model.remote.model.activity.InquiryStudentActivityModel
import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
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

    override suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.rejectStudentActivityInfo(
            id = id
        )
    }

    override suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.deleteStudentActivityInfo(
            id = id
        )
    }

    override suspend fun inquiryMyStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String
    ): Flow<InquiryStudentActivityListResponse> {
        return activityDataSource.inquiryMyStudentActivityInfoList(
            page = page,
            size = size,
            sort = sort
        )
    }

    override suspend fun inquiryStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String,
        id: UUID
    ): Flow<InquiryStudentActivityListResponse> {
        return activityDataSource.inquiryStudentActivityInfoList(
            page = page,
            size = size,
            sort = sort,
            id = id
        )
    }

    override suspend fun inquiryEntireStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String
    ): Flow<InquiryStudentActivityListResponse> {
        return activityDataSource.inquiryEntireStudentActivityInfoList(
            page = page,
            size = size,
            sort = sort
        )
    }
}