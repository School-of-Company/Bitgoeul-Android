package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.remote.request.lecture.OpenLectureRequest
import javax.inject.Inject

class OpenLectureUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(body: OpenLectureRequest) = kotlin.runCatching {
        lectureRepository.openLecture(body = body)
    }
}