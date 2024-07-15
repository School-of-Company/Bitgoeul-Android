package com.msg.domain.usecase.school

import com.msg.data.repository.school.SchoolRepository
import com.msg.model.param.school.PostSchoolParam
import javax.inject.Inject

class PostSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    operator fun invoke(body: PostSchoolParam) = runCatching {
        schoolRepository.postSchool(body = body)
    }
}