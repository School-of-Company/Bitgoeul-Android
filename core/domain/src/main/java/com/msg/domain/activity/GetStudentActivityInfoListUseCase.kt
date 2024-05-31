package com.msg.domain.activity

import com.msg.data.repository.activity.ActivityRepository
import java.util.UUID
import javax.inject.Inject

class GetStudentActivityInfoListUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(page: Int, size: Int, id: UUID) = runCatching {
        activityRepository.getStudentActivityInfoList(
            page = page,
            size = size,
            id = id
        )
    }
}