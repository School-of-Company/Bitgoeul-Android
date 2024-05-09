package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class GetLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(page: Int, size: Int, type: LectureType?) =
        runCatching {
            lectureRepository.getLectureList(page = page, size = size, type = type)
        }
}
