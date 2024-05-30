package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class EditLectureCourseCompletionStatusUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(id: UUID, studentId: UUID, isComplete: Boolean) = runCatching {
        lectureRepository.editLectureCourseCompletionStatus(id = id, studentId = studentId, isComplete = isComplete)
    }
}