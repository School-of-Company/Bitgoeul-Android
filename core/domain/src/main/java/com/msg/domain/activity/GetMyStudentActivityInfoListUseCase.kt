package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import javax.inject.Inject

class GetMyStudentActivityInfoListUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(page: Int, size: Int) = runCatching {
        activityRepository.getMyStudentActivityInfoList(
            page = page,
            size = size,
        )
    }
}