package com.msg.network.datasource.lecture

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.network.api.LectureAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class LectureDataSourceImpl @Inject constructor(
    private val lectureAPI: LectureAPI,
) : LectureDataSource {
    override suspend fun openLecture(body: OpenLectureRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.openLecture(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getLectureList(
        page: Int,
        size: Int,
        status: ApproveStatus,
        type: LectureType,
    ): Flow<List<LectureListResponse>> = flow {
        emit(
            BitgoeulApiHandler<List<LectureListResponse>>()
                .httpRequest {
                    lectureAPI.getLectureList(
                        page = page,
                        size = size,
                        status = status,
                        type = type
                    )
                }
                .sendRequest()
        )
    }

    override suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse> = flow {
        emit(
            BitgoeulApiHandler<DetailLectureResponse>()
                .httpRequest { lectureAPI.getDetailLecture(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun lectureApplication(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.lectureApplication(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun approvePendingLecture(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.approvePendingLecture(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun rejectPendingLecture(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.rejectPendingLecture(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}