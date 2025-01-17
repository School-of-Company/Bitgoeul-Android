package com.msg.data.repository.lecture

import com.msg.data.mapper.lecture.toEntity
import com.msg.model.entity.lecture.DetailLectureEntity
import com.msg.model.entity.lecture.DownloadExcelFileEntity
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.entity.lecture.GetTakingLectureStudentListEntity
import com.msg.model.entity.lecture.LectureListEntity
import com.msg.network.datasource.lecture.LectureDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDataSource: LectureDataSource,
) : LectureRepository {
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