package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.remote.enumdatatype.Division
import javax.inject.Inject

class SearchLineUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(keyword: String, division: Division) = runCatching {
        lectureRepository.searchLine(keyword = keyword, division = division)
    }
}
