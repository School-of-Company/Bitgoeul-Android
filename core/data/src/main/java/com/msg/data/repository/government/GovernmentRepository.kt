package com.msg.data.repository.government

import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.param.government.PostGovernmentParam
import kotlinx.coroutines.flow.Flow

interface GovernmentRepository {
    fun getGovernment(): Flow<GetGovernmentEntity>
    fun postGovernment(body: PostGovernmentParam): Flow<Unit>
    fun deleteGovernment(id: Long): Flow<Unit>
}