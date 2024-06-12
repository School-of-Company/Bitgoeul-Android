package com.msg.domain.usecase.activity

import com.msg.data.repository.activity.ActivityRepository
import com.msg.model.model.activity.StudentActivityModel
import javax.inject.Inject

class AddStudentActivityInfoUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(body: StudentActivityModel) = runCatching {
        activityRepository.addStudentActivityInfo(body = body)
    }
}