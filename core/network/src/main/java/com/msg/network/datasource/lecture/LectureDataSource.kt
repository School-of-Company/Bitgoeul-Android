package com.msg.network.datasource.lecture

import com.msg.network.response.lecture.*
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureDataSource {
    fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListResponse>
    fun getDetailLecture(id: UUID): Flow<DetailLectureResponse>
    fun lectureApplication(id: UUID): Flow<Unit>
    fun lectureApplicationCancel(id: UUID): Flow<Unit>
    fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryResponse>
    fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListResponse>
    fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit>
    fun downloadExcelFile(): Flow<DownloadExcelFileResponse>
}