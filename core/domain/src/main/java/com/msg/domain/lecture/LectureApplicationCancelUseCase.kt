package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class LectureApplicationCancelUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(id: UUID) = runCatching {
            lectureRepository.lectureApplicationCancel(id = id)
        }
}