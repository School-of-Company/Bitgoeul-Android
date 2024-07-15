package com.msg.domain.usecase.school

import com.msg.data.repository.school.SchoolRepository
import javax.inject.Inject

class DeleteSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    operator fun invoke(id: Long) = runCatching {
        schoolRepository.deleteSchool(id = id)
    }
}