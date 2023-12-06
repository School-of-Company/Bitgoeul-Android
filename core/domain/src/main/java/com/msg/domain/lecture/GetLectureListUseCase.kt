package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import javax.inject.Inject

class GetLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke(page: Int, size: Int, status: ApproveStatus, type: LectureType) = runCatching {
        lectureRepository.getLectureList(page = page, size = size, status = status, type = type)
    }
}