package com.msg.network.datasource.lecture

import com.msg.network.response.lecture.*
import com.msg.network.request.lecture.OpenLectureRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    fun openLecture(body: OpenLectureRequest): Flow<Unit>
    fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListResponse>
    fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    fun patchLecture(id: UUID, body: OpenLectureRequest): Flow<Unit>
    fun lectureApplication(id: UUID): Flow<Unit>
    fun lectureApplicationCancel(id: UUID): Flow<Unit>
    fun searchProfessor(keyword: String): Flow<SearchProfessorResponse>
    fun searchLine(keyword: String, division: String): Flow<SearchLineResponse>
    fun searchDepartment(keyword: String): Flow<SearchDepartmentResponse>
    fun searchDivision(keyword: String): Flow<SearchDivisionResponse>
    fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse>
    fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse>
    fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit>
    fun downloadExcelFile(): Flow<DownloadExcelFileResponse>
}