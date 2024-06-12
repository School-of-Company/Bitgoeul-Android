package com.msg.domain.usecase.activity

import com.msg.data.repository.activity.ActivityRepository
import com.msg.model.model.activity.StudentActivityModel
import java.util.UUID
import javax.inject.Inject

class EditStudentActivityInfoUseCase  @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(id: UUID,body: StudentActivityModel) = runCatching {
        activityRepository.editStudentActivityInfo(id = id, body = body)
    }
}