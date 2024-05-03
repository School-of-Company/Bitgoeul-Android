package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class EditPostUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(id: UUID, studentId: UUID, isComplete: Boolean) = runCatching {
        lectureRepository.editPost(id = id, studentId = studentId, isComplete = isComplete)
    }
}