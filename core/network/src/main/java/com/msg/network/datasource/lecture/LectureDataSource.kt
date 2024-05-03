package com.msg.network.datasource.lecture

import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.model.remote.response.lecture.GetTakingLectureStudentListResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchDivisionResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    suspend fun openLecture(body: OpenLectureRequest): Flow<Unit>
    suspend fun getLectureList(page: Int, size: Int, type: LectureType?): Flow<LectureListResponse>
    suspend fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    suspend fun lectureApplication(id: UUID): Flow<Unit>
    suspend fun lectureApplicationCancel(id: UUID): Flow<Unit>
    suspend fun searchProfessor(keyword: String): Flow<SearchProfessorResponse>
    suspend fun searchLine(keyword: String, division: String): Flow<SearchLineResponse>
    suspend fun searchDepartment(keyword: String): Flow<SearchDepartmentResponse>
    suspend fun searchDivision(keyword: String): Flow<SearchDivisionResponse>
    suspend fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse>
    suspend fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse>
    suspend fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit>
}