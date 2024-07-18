package com.msg.network.datasource.government

import com.msg.network.request.government.PostGovernmentRequest
import com.msg.network.response.government.GetGovernmentResponse
import kotlinx.coroutines.flow.Flow

interface GovernmentDataSource {
    fun getGovernment(): Flow<GetGovernmentResponse>
    fun postGovernment(body: PostGovernmentRequest): Flow<Unit>
    fun deleteGovernment(id: Long): Flow<Unit>
}