package com.msg.network.datasource.activity

import com.msg.model.model.activity.StudentActivityModel
import com.msg.network.api.ActivityAPI
import com.msg.network.response.activity.*
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ActivityDataSourceImpl @Inject constructor(
    private val activityAPI: ActivityAPI
) : ActivityDataSource {
    override fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> =
        makeRequest { activityAPI.addStudentActivityInfo(body = body) }

    override fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> =
        makeRequest { activityAPI.editStudentActivityInfo(id = id, body = body) }

    override fun approveStudentActivityInfo(id: UUID): Flow<Unit> =
        makeRequest { activityAPI.approveStudentActivityInfo(id = id) }

    override fun rejectStudentActivityInfo(id: UUID): Flow<Unit> =
        makeRequest { activityAPI.rejectStudentActivityInfo(id = id) }

    override fun deleteStudentActivityInfo(id: UUID): Flow<Unit> =
        makeRequest { activityAPI.deleteStudentActivityInfo(id = id) }

    override fun getMyStudentActivityInfoList(
        page: Int,
        size: Int
    ): Flow<GetStudentActivityListResponse> =
        makeRequest { activityAPI.getMyStudentActivityInfoList(page = page, size = size) }

    override fun getStudentActivityInfoList(
        page: Int,
        size: Int,
        id: UUID
    ): Flow<GetStudentActivityListResponse> =
        makeRequest { activityAPI.getStudentActivityInfoList(page = page, size = size, id = id) }

    override fun getEntireStudentActivityInfoList(
        page: Int,
        size: Int
    ): Flow<GetStudentActivityListResponse> =
        makeRequest { activityAPI.getEntireStudentActivityInfoList(page = page, size = size) }

    override fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoResponse> =
        makeRequest { activityAPI.getDetailStudentActivityInfo(id = id) }
}