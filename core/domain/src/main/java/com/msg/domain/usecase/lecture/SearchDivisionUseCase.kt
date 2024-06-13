package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class SearchDivisionUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke(keyword: String) = runCatching {
        lectureRepository.searchDivision(keyword = keyword)
    }
}