package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import javax.inject.Inject

class GetLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(page: Int, size: Int, status: ApproveStatus, type: LectureType) = runCatching {
<<<<<<< HEAD
            lectureRepository.getLectureList(page = page, size = size, status = status, type = type)
        }
=======
        lectureRepository.getLectureList(page = page, size = size, status = status, type = type)
    }
>>>>>>> 8751c3f9e0738aa7bd07d4176a4d202d41215349
}