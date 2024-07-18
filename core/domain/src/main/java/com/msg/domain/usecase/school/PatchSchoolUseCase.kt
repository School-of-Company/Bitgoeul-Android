package com.msg.domain.usecase.school

import com.msg.data.repository.school.SchoolRepository
import com.msg.model.param.school.PatchSchoolParam
import java.util.UUID
import javax.inject.Inject

class PatchSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    operator fun invoke(id: Long, body: PatchSchoolParam) = runCatching {
        schoolRepository.patchSchool(id = id, body = body)
    }
}