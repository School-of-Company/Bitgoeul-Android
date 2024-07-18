package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import com.msg.model.param.university.PostUniversityParam
import javax.inject.Inject

class PatchUniversityUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(id: Long, body: PostUniversityParam) = runCatching {
        universityRepository.patchUniversity(id = id, body = body)
    }
}