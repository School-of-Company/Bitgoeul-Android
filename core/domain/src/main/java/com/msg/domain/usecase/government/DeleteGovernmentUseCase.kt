package com.msg.domain.usecase.government

import com.msg.data.repository.government.GovernmentRepository
import javax.inject.Inject

class DeleteGovernmentUseCase @Inject constructor(
    private val governmentRepository: GovernmentRepository
) {
    operator fun invoke(id: Long) = runCatching {
        governmentRepository.deleteGovernment(id = id)
    }
}