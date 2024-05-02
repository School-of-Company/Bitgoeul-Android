package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class SearchLineUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(keyword: String, division: String) = runCatching {
        lectureRepository.searchLine(keyword = keyword, division = division)
    }
}
