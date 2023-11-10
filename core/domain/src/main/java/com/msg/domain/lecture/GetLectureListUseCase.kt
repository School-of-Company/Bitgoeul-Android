package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class GetLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        lectureRepository.getLectureList()
    }
}