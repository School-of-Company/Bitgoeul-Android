package com.msg.data.repository.government

import com.msg.data.mapper.government.toEntity
import com.msg.data.mapper.government.toRequest
import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.param.government.PostGovernmentParam
import com.msg.network.datasource.government.GovernmentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GovernmentRepositoryImpl @Inject constructor(
    private val governmentDataSource: GovernmentDataSource
) : GovernmentRepository
{

    override fun getGovernment(): Flow<GetGovernmentEntity> {
        return governmentDataSource.getGovernment().transform {
            emit(it.toEntity())
        }
    }

    override fun postGovernment(body: PostGovernmentParam): Flow<Unit> {
        return governmentDataSource.postGovernment(body = body.toRequest())
    }

    override fun deleteGovernment(id: Long): Flow<Unit> {
        return governmentDataSource.deleteGovernment(id = id)
    }
}