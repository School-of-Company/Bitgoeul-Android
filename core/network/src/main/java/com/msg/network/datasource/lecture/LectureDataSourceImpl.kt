package com.msg.network.datasource.lecture

import com.msg.network.response.lecture.*
import com.msg.network.api.LectureAPI
import com.msg.network.request.lecture.OpenLectureRequest
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class LectureDataSourceImpl @Inject constructor(
    private val lectureAPI: LectureAPI,
) : LectureDataSource {
    override fun openLecture(body: OpenLectureRequest): Flow<Unit> =
        makeRequest { lectureAPI.openLecture(body) }

    override fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListResponse> =
        makeRequest { lectureAPI.getLectureList(page, size, type) }

    override fun getDetailLecture(id: UUID): Flow<DetailLectureResponse> =
        makeRequest { lectureAPI.getDetailLecture(id) }

    override fun patchLecture(id: UUID, body: OpenLectureRequest): Flow<Unit> =
        makeRequest { lectureAPI.patchLecture(id = id, body = body) }

    override fun lectureApplication(id: UUID): Flow<Unit> =
        makeRequest { lectureAPI.lectureApplication(id) }

    override fun lectureApplicationCancel(id: UUID): Flow<Unit> =
        makeRequest { lectureAPI.lectureApplicationCancel(id) }

    override fun searchProfessor(keyword: String): Flow<SearchProfessorResponse> =
        makeRequest { lectureAPI.searchProfessor(keyword) }

    override fun searchLine(keyword: String, division: String): Flow<SearchLineResponse> =
        makeRequest { lectureAPI.searchLine(keyword, division) }

    override fun searchDepartment(keyword: String): Flow<SearchDepartmentResponse> =
        makeRequest { lectureAPI.searchDepartment(keyword) }

    override fun searchDivision(keyword: String): Flow<SearchDivisionResponse> =
        makeRequest { lectureAPI.searchDivision(keyword) }

    override fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse> =
        makeRequest { lectureAPI.getLectureSignUpHistory(studentId) }

    override fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse> =
        makeRequest { lectureAPI.getTakingLectureStudentList(id) }

    override fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit> =
        makeRequest { lectureAPI.editLectureCourseCompletionStatus(id, studentId, isComplete) }

    override fun downloadExcelFile(): Flow<DownloadExcelFileResponse> =
        makeRequest { lectureAPI.downloadExcelFile() }
}