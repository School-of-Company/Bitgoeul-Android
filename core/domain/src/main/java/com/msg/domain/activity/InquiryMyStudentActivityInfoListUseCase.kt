package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import javax.inject.Inject

class InquiryMyStudentActivityInfoListUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(page: Int, size: Int, sort: String) = runCatching {
        activityRepository.inquiryMyStudentActivityInfoList(
            page = page,
            size = size,
            sort = sort
        )
    }
}