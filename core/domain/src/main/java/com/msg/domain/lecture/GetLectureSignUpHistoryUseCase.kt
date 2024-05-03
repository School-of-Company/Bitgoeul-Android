package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class GetLectureSignUpHistoryUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke(studentId: UUID) = runCatching {
        lectureRepository.getLectureSignUpHistory(studentId = studentId)
    }
}