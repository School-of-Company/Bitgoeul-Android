package com.msg.data.repository.lecture

import com.msg.model.remote.model.LectureListModel
import com.msg.model.remote.request.lecture.OpenLectureRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureRepository {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
    suspend fun getLectureList(): Flow<List<LectureListModel>>
    suspend fun getDetailLecture(id: UUID): Flow<Unit>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
    suspend fun approvePendingLecture(id: UUID): Flow<Unit>
    suspend fun rejectPendingLecture(id: UUID): Flow<Unit>
}