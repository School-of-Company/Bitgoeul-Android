package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import javax.inject.Inject

class DeleteDepartmentUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(id: Long, department: String) = runCatching {
        universityRepository.deleteDepartment(id = id, department = department)
    }
}