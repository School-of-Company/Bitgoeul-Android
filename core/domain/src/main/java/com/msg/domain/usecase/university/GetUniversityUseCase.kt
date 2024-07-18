package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import javax.inject.Inject

class GetUniversityUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke() = runCatching {
        universityRepository.getUniversity()
    }
}