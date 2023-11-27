package com.msg.network.datasource.lecture

import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
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

    override suspend fun getLectureList(): Flow<List<LectureListModel>> = flow {
        emit(
            BitgoeulApiHandler<List<LectureListModel>>()
                .httpRequest { lectureAPI.getLectureList() }
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