package com.msg.network.datasource.lecture

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
<<<<<<< HEAD
    suspend fun getLectureList(page: Int, size: Int, status: ApproveStatus, type: LectureType): Flow<List<LectureListResponse>>
=======
    suspend fun getLectureList(page: Int, size: Int, status: ApproveStatus, type: LectureType, ): Flow<List<LectureListResponse>>
>>>>>>> 8751c3f9e0738aa7bd07d4176a4d202d41215349
    suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
    suspend fun approvePendingLecture(id: UUID): Flow<Unit>
    suspend fun rejectPendingLecture(id: UUID): Flow<Unit>
}