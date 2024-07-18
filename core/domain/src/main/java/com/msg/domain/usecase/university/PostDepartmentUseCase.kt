package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import com.msg.model.param.university.PostDepartmentParam
import javax.inject.Inject

class PostDepartmentUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(body: PostDepartmentParam) = runCatching {
        universityRepository.postDepartment(body = body)
    }
}