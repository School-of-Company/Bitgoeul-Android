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
import com.msg.network.datasource.lecture.LectureDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDataSource: LectureDataSource,
) : LectureRepository {
    override fun openLecture(body: OpenLectureRequest): Flow<Unit> {
        return lectureDataSource.openLecture(
            body = body
        )
    }

    override fun getLectureList(
        page: Int,
        size: Int,
        type: String?,
    ): Flow<LectureListResponse> {
        return lectureDataSource.getLectureList(
            page = page,
            size = size,
            type = type
        )
    }

    override fun getDetailLecture(id: UUID): Flow<DetailLectureResponse> {
        return lectureDataSource.getDetailLecture(
            id = id
        )
    }

    override fun lectureApplication(id: UUID): Flow<Unit> {
        return lectureDataSource.lectureApplication(
            id = id
        )
    }

    override fun lectureApplicationCancel(id: UUID): Flow<Unit> {
        return lectureDataSource.lectureApplicationCancel(
            id = id
        )
    }

    override fun searchProfessor(keyword: String): Flow<SearchProfessorResponse> {
        return lectureDataSource.searchProfessor(
            keyword = keyword
        )
    }

    override fun searchLine(keyword: String, division: String): Flow<SearchLineResponse> {
        return lectureDataSource.searchLine(
            keyword = keyword,
            division = division
        )
    }

    override fun searchDepartment(keyword: String): Flow<SearchDepartmentResponse> {
        return lectureDataSource.searchDepartment(
            keyword = keyword
        )
    }

    override fun searchDivision(keyword: String): Flow<SearchDivisionResponse> {
        return lectureDataSource.searchDivision(
            keyword = keyword
        )
    }

    override fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse> {
        return lectureDataSource.getLectureSignUpHistory(
            studentId = studentId
        )
    }

    override fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse> {
        return lectureDataSource.getTakingLectureStudentList(
            id = id
        )
    }

    override fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit> {
        return lectureDataSource.editLectureCourseCompletionStatus(
            id = id,
            studentId = studentId,
            isComplete = isComplete
        )
    }

    override fun downloadExcelFile(): Flow<DownloadExcelFileResponse> {
        return lectureDataSource.downloadExcelFile()
    }
}