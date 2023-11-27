package com.msg.network.datasource.lecture

import com.msg.model.remote.model.lecture.LectureListModel
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
    suspend fun getLectureList(): Flow<List<LectureListModel>>
    suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
    suspend fun approvePendingLecture(id: UUID): Flow<Unit>
    suspend fun rejectPendingLecture(id: UUID): Flow<Unit>
}