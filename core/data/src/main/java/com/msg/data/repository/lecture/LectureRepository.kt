package com.msg.data.repository.lecture

import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.DownloadExcelFileResponse
import com.msg.model.remote.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.model.remote.response.lecture.GetTakingLectureStudentListResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchDivisionResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureRepository {
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