package com.msg.data.repository.lecture

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.network.datasource.lecture.LectureDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDataSource: LectureDataSource,
) : LectureRepository {
    override suspend fun openLecture(body: OpenLectureRequest): Flow<Unit> {
        return lectureDataSource.openLecture(
            body = body
        )
    }

    override suspend fun getLectureList(page: Int, size: Int, status: ApproveStatus, type: LectureType): Flow<List<LectureListResponse>> {
        return lectureDataSource.getLectureList(page = page, size = size, status = status, type = type)
    }

    override suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse> {
        return lectureDataSource.getDetailLecture(
            id = id
        )
    }

    override suspend fun lectureApplication(id: UUID): Flow<Unit> {
        return lectureDataSource.lectureApplication(
            id = id
        )
    }

    override suspend fun approvePendingLecture(id: UUID): Flow<Unit> {
        return lectureDataSource.approvePendingLecture(
            id = id
        )
    }

    override suspend fun rejectPendingLecture(id: UUID): Flow<Unit> {
        return lectureDataSource.rejectPendingLecture(
            id = id
        )
    }
}