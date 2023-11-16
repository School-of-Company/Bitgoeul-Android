package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.network.api.ActivityAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ActivityDataSourceImpl @Inject constructor(
    private val activityAPI: ActivityAPI
) : ActivityDataSource {
    override suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.addStudentActivityInfo(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}