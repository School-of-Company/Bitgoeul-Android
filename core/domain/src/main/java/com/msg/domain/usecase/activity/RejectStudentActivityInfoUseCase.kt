package com.msg.domain.usecase.activity

import com.msg.data.repository.activity.ActivityRepository
import java.util.UUID
import javax.inject.Inject

class RejectStudentActivityInfoUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        activityRepository.rejectStudentActivityInfo(id = id)
    }
}