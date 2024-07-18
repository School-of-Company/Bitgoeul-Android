package com.msg.domain.usecase.government

import com.msg.data.repository.government.GovernmentRepository
import com.msg.model.param.government.PostGovernmentParam
import javax.inject.Inject

class PostGovernmentUseCase @Inject constructor(
    private val governmentRepository: GovernmentRepository
) {
    operator fun invoke(body: PostGovernmentParam) = runCatching {
        governmentRepository.postGovernment(body = body)
    }
}