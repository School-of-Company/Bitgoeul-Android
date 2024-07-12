package com.msg.domain.usecase.school

import com.msg.data.repository.school.SchoolRepository
import javax.inject.Inject

class GetSchoolNameUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    operator fun invoke() = runCatching {
        schoolRepository.getSchoolName()
    }
}