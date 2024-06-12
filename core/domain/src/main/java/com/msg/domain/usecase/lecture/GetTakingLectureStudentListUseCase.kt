package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class GetTakingLectureStudentListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        lectureRepository.getTakingLectureStudentList(id = id)
    }
}