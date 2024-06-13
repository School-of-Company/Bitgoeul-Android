package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class GetSearchLineUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(keyword: String, division: String) = runCatching {
        lectureRepository.getSearchLine(keyword = keyword, division = division)
    }
}
