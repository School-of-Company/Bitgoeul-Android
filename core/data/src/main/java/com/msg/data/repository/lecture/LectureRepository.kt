package com.msg.data.repository.lecture

import com.msg.model.entity.lecture.DetailLectureEntity
import com.msg.model.entity.lecture.DownloadExcelFileEntity
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.entity.lecture.GetTakingLectureStudentListEntity
import com.msg.model.entity.lecture.LectureListEntity
import com.msg.model.entity.lecture.SearchDepartmentEntity
import com.msg.model.entity.lecture.SearchDivisionEntity
import com.msg.model.entity.lecture.SearchLineEntity
import com.msg.model.entity.lecture.SearchProfessorEntity
import com.msg.model.param.lecture.OpenLectureParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LectureRepository {
    fun openLecture(body: OpenLectureParam): Flow<Unit>
    fun getLectureList(page: Int, size: Int, type: String?): Flow<LectureListEntity>
    fun getDetailLecture(id: UUID): Flow<DetailLectureEntity>
    fun lectureApplication(id: UUID): Flow<Unit>
    fun lectureApplicationCancel(id: UUID): Flow<Unit>
    fun getSearchProfessor(keyword: String): Flow<SearchProfessorEntity>
    fun getSearchLine(keyword: String, division: String): Flow<SearchLineEntity>
    fun getSearchDepartment(keyword: String): Flow<SearchDepartmentEntity>
    fun getSearchDivision(keyword: String): Flow<SearchDivisionEntity>
    fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryEntity>
    fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListEntity>
    fun editLectureCourseCompletionStatus(id: UUID, studentId: UUID, isComplete: Boolean): Flow<Unit>
    fun downloadExcelFile(): Flow<DownloadExcelFileEntity>
}