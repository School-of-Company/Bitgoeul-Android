package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.param.lecture.OpenLectureParam
import javax.inject.Inject

class OpenLectureUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(body: OpenLectureParam) = runCatching {
        lectureRepository.openLecture(body = body)
    }
}