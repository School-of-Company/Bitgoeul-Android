package com.msg.network.datasource.user

import com.msg.network.api.UserAPI
import com.msg.network.request.user.ChangePasswordRequest
import com.msg.network.response.user.GetMyPageResponse
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userAPI: UserAPI
) : UserDataSource {
    override fun changePassword(body: ChangePasswordRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { userAPI.changePassword(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun inquiryMyPage(): Flow<GetMyPageResponse> = flow {
        emit(
            BitgoeulApiHandler<GetMyPageResponse>()
                .httpRequest { userAPI.inquiryMyPage() }
                .sendRequest()
        )
    }
}