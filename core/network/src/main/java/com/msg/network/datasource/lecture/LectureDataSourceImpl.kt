package com.msg.network.datasource.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.model.lecture.SearchResponseModel
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
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
        type: LectureType,
    ): Flow<LectureListResponse> = flow {
        emit(
            BitgoeulApiHandler<LectureListResponse>()
                .httpRequest {
                    lectureAPI.getLectureList(
                        page = page,
                        size = size,
                        type = type
                    )
                }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

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

    override suspend fun lectureApplicationCancel(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.lectureApplicationCancel(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun searchProfessor(keyword: String): Flow<List<SearchProfessorResponse>> =
        flow {
            emit(
                BitgoeulApiHandler<List<SearchProfessorResponse>>()
                    .httpRequest { lectureAPI.searchProfessor(keyword = keyword) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun searchLine(keyword: String, division: Division): Flow<SearchResponseModel> = flow {
        emit(
            BitgoeulApiHandler<SearchResponseModel>()
                .httpRequest { lectureAPI.searchLine(keyword = keyword, division = division) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun searchDepartment(keyword: String): Flow<SearchResponseModel> = flow {
        emit(
            BitgoeulApiHandler<SearchResponseModel>()
                .httpRequest { lectureAPI.searchDepartment(keyword = keyword) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}