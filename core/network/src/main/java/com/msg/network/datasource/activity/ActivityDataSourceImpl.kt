package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
import com.msg.network.api.ActivityAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class ActivityDataSourceImpl @Inject constructor(
    private val activityAPI: ActivityAPI
) : ActivityDataSource {
    override suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.addStudentActivityInfo(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.editStudentActivityInfo(id = id, body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.approveStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.rejectStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.deleteStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getMyStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<GetStudentActivityListResponse>()
                .httpRequest { activityAPI.getMyStudentActivityInfoList(page = page, size = size) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getStudentActivityInfoList(
        page: Int,
        size: Int,
        id: UUID
    ): Flow<GetStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<GetStudentActivityListResponse>()
                .httpRequest { activityAPI.getStudentActivityInfoList(page = page, size = size, id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getEntireStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<GetStudentActivityListResponse>()
                .httpRequest { activityAPI.getEntireStudentActivityInfoList(page = page, size = size) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoResponse> = flow {
        emit(
            BitgoeulApiHandler<GetDetailStudentActivityInfoResponse>()
                .httpRequest { activityAPI.getDetailStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}