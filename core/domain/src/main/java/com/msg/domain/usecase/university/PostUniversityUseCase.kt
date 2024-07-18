package com.msg.domain.usecase.university

import com.msg.data.repository.university.UniversityRepository
import com.msg.model.param.university.PostUniversityParam
import javax.inject.Inject

class PostUniversityUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(body: PostUniversityParam) = runCatching {
        universityRepository.postUniversity(body = body)
    }
}