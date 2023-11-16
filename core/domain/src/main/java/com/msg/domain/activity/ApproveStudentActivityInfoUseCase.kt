package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import java.util.UUID
import javax.inject.Inject

class ApproveStudentActivityInfoUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        activityRepository.approveStudentActivityInfo(id = id)
    }
}