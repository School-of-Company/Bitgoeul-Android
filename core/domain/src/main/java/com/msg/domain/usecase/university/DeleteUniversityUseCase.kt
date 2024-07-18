package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import javax.inject.Inject

class DeleteUniversityUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(id: Long) = runCatching {
        universityRepository.deleteUniversity(id = id)
    }
}