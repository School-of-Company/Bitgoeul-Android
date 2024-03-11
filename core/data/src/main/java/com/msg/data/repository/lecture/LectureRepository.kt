package com.msg.data.repository.lecture

import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureRepository {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
    suspend fun getLectureList(page: Int, size: Int, type: LectureType): Flow<LectureListResponse>
    suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
}