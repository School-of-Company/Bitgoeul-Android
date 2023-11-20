package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import java.util.UUID
import javax.inject.Inject

class InquiryStudentActivityInfoListUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(page: Int, size: Int, sort: String, id: UUID) = runCatching {
        activityRepository.inquiryStudentActivityInfoList(
            page = page,
            size = size,
            sort = sort,
            id = id
        )
    }
}