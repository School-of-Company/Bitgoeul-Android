package com.msg.network.datasource.government

import com.msg.network.api.GovernmentAPI
import com.msg.network.request.government.PostGovernmentRequest
import com.msg.network.response.government.GetGovernmentResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GovernmentDataSourceImpl @Inject constructor(
    private val governmentAPI: GovernmentAPI
) : GovernmentDataSource {

    override fun getGovernment(): Flow<GetGovernmentResponse> =
        makeRequest { governmentAPI.getGovernment() }

    override fun postGovernment(body: PostGovernmentRequest): Flow<Unit> =
        makeRequest { governmentAPI.postGovernment(body = body) }

    override fun deleteGovernment(id: Long): Flow<Unit> =
        makeRequest { governmentAPI.deleteGovernment(id = id) }
}