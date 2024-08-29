package com.msg.data.repository.lecture

import com.msg.model.entity.lecture.DetailLectureEntity
import com.msg.model.entity.lecture.DownloadExcelFileEntity
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.entity.lecture.GetTakingLectureStudentListEntity
import com.msg.model.entity.lecture.LectureListEntity
// import com.msg.model.param.lecture.OpenLectureParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureRepository {
    fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListEntity>
    fun getDetailLecture(id: UUID): Flow<DetailLectureEntity>
    // fun patchLecture(id: UUID, body: OpenLectureParam): Flow<Unit>
    fun lectureApplication(id: UUID): Flow<Unit>
    fun lectureApplicationCancel(id: UUID): Flow<Unit>
    fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryEntity>
    fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListEntity>
    fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit>
    fun downloadExcelFile(): Flow<DownloadExcelFileEntity>
}