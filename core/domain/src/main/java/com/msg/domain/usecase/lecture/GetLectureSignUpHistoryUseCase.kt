package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GetLectureSignUpHistoryUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    operator fun invoke(studentId: UUID): Flow<GetLectureSignUpHistoryEntity> =
        lectureRepository.getLectureSignUpHistory(studentId = studentId)
}