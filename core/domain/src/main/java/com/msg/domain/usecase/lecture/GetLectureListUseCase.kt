package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class GetLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    operator fun invoke(page: Int, size: Int, type: String?) = runCatching {
        lectureRepository.getLectureList(page = page, size = size, type = type)
    }
}
