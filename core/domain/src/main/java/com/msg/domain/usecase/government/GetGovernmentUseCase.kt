package com.msg.domain.usecase.government

import com.msg.data.repository.government.GovernmentRepository
import javax.inject.Inject

class GetGovernmentUseCase @Inject constructor(
    private val governmentRepository: GovernmentRepository
) {
    operator fun invoke() = runCatching {
        governmentRepository.getGovernment()
    }
}