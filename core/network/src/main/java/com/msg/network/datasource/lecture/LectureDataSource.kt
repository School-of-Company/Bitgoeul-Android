package com.msg.network.datasource.lecture

import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.model.lecture.SearchResponseModel
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
    suspend fun getLectureList(page: Int, size: Int, type: LectureType): Flow<LectureListResponse>
    suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
    suspend fun lectureApplicationCancel(id: UUID): Flow<Unit>
    suspend fun searchProfessor(keyword: String): Flow<List<SearchProfessorResponse>>
    suspend fun searchLine(keyword: String, division: Division): Flow<SearchResponseModel>
    suspend fun searchDepartment(keyword: String): Flow<SearchResponseModel>
}