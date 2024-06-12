package com.msg.domain.usecase.lecture

import com.msg.data.repository.lecture.LectureRepository
import javax.inject.Inject

class DownloadExcelFileUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke() = runCatching {
        lectureRepository.downloadExcelFile()
    }
}