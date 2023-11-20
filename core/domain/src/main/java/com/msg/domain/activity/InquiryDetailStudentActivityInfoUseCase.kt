package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import java.util.UUID
import javax.inject.Inject

class InquiryDetailStudentActivityInfoUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        activityRepository.inquiryDetailStudentActivityInfo(id = id)
    }
}