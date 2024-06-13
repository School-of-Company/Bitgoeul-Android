package com.msg.network.datasource.lecture

import com.msg.network.response.lecture.*
import com.msg.network.api.LectureAPI
import com.msg.network.request.lecture.OpenLectureRequest
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
    override fun openLecture(body: OpenLectureRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.openLecture(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun getLectureList(
        page: Int,
        size: Int,
        type: String?,
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

    override fun getDetailLecture(id: UUID): Flow<DetailLectureResponse> = flow {
        emit(
            BitgoeulApiHandler<DetailLectureResponse>()
                .httpRequest { lectureAPI.getDetailLecture(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun lectureApplication(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.lectureApplication(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun lectureApplicationCancel(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { lectureAPI.lectureApplicationCancel(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun searchProfessor(keyword: String): Flow<SearchProfessorResponse> =
        flow {
            emit(
                BitgoeulApiHandler<SearchProfessorResponse>()
                    .httpRequest { lectureAPI.getSearchProfessor(keyword = keyword) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun searchLine(keyword: String, division: String): Flow<SearchLineResponse> =
        flow {
            emit(
                BitgoeulApiHandler<SearchLineResponse>()
                    .httpRequest { lectureAPI.getSearchLine(keyword = keyword, division = division) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun searchDepartment(keyword: String): Flow<SearchDepartmentResponse> = flow {
        emit(
            BitgoeulApiHandler<SearchDepartmentResponse>()
                .httpRequest { lectureAPI.getSearchDepartment(keyword = keyword) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun searchDivision(keyword: String): Flow<SearchDivisionResponse> = flow {
        emit(
            BitgoeulApiHandler<SearchDivisionResponse>()
                .httpRequest { lectureAPI.getSearchDivision(keyword = keyword) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse> =
        flow {
            emit(
                BitgoeulApiHandler<GetLectureSignUpHistoryResponse>()
                    .httpRequest { lectureAPI.getLectureSignUpHistory(studentId = studentId) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse> =
        flow {
            emit(
                BitgoeulApiHandler<GetTakingLectureStudentListResponse>()
                    .httpRequest { lectureAPI.getTakingLectureStudentList(id = id) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit> =
        flow {
            emit(
                BitgoeulApiHandler<Unit>()
                    .httpRequest {
                        lectureAPI.editLectureCourseCompletionStatus(
                            id = id,
                            studentId = studentId,
                            isComplete = isComplete
                        )
                    }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)

    override fun downloadExcelFile(): Flow<DownloadExcelFileResponse> = flow {
        emit(
            BitgoeulApiHandler<DownloadExcelFileResponse>()
                .httpRequest {
                    lectureAPI.downloadExcelFile()
                }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}