package com.msg.network.datasource.lecture

import com.msg.network.request.lecture.OpenLectureRequest
import com.msg.network.response.lecture.DetailLectureResponse
import com.msg.network.response.lecture.DownloadExcelFileResponse
import com.msg.network.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.network.response.lecture.GetTakingLectureStudentListResponse
import com.msg.network.response.lecture.LectureListResponse
import com.msg.network.response.lecture.SearchDepartmentResponse
import com.msg.network.response.lecture.SearchDivisionResponse
import com.msg.network.response.lecture.SearchLineResponse
import com.msg.network.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    fun openLecture(body: OpenLectureRequest): Flow<Unit>
    fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListResponse>
    fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
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