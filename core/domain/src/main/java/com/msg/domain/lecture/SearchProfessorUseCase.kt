package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class SearchProfessorUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(keyword: String) = runCatching {
        lectureRepository.searchProfessor(keyword = keyword)
    }
}
