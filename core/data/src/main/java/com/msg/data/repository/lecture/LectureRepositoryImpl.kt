package com.msg.data.repository.lecture

import com.msg.data.mapper.lecture.toEntity
import com.msg.data.mapper.lecture.toRequest
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
import com.msg.network.datasource.lecture.LectureDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDataSource: LectureDataSource,
) : LectureRepository {
    override fun openLecture(body: OpenLectureParam): Flow<Unit> {
        return lectureDataSource.openLecture(
            body = body.toRequest()
        )
    }

    override fun getLectureList(
        page: Int,
        size: Int,
        type: String?,
    ): Flow<LectureListEntity> {
        return lectureDataSource.getLectureList(
            page = page,
            size = size,
            type = type
        ).transform { response ->
            emit(response.toEntity())
        }
    }

    override fun getDetailLecture(id: UUID): Flow<DetailLectureEntity> {
        return lectureDataSource.getDetailLecture(
            id = id
        ).transform { response ->
            emit(response.toEntity())
        }
    }

    override fun patchLecture(id: UUID, body: OpenLectureParam): Flow<Unit> {
        return lectureDataSource.patchLecture(
            id = id,
            body = body.toRequest()
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

    override fun searchProfessor(keyword: String): Flow<SearchProfessorEntity> {
        return lectureDataSource.searchProfessor(
            keyword = keyword
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun searchLine(keyword: String, division: String): Flow<SearchLineEntity> {
        return lectureDataSource.searchLine(
            keyword = keyword,
            division = division
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun searchDepartment(keyword: String): Flow<SearchDepartmentEntity> {
        return lectureDataSource.searchDepartment(
            keyword = keyword
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun searchDivision(keyword: String): Flow<SearchDivisionEntity> {
        return lectureDataSource.searchDivision(
            keyword = keyword
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getLectureSignUpHistory(studentId: UUID): Flow<GetLectureSignUpHistoryEntity> {
        return lectureDataSource.getLectureSignUpHistory(studentId)
            .transform { response ->
                response.toEntity()
            }
    }


    override fun getTakingLectureStudentList(id: UUID): Flow<GetTakingLectureStudentListEntity> {
        return lectureDataSource.getTakingLectureStudentList(
            id = id
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun editLectureCourseCompletionStatus(
        id: UUID,
        studentId: UUID,
        isComplete: Boolean,
    ): Flow<Unit> {
        return lectureDataSource.editLectureCourseCompletionStatus(
            id = id,
            studentId = studentId,
            isComplete = isComplete
        )
    }

    override fun downloadExcelFile(): Flow<DownloadExcelFileEntity> {
        return lectureDataSource.downloadExcelFile().transform { response ->
            response.toEntity()
        }
    }
}