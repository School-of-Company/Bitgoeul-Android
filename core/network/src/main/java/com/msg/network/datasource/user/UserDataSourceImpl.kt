package com.msg.network.datasource.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.user.InquiryMyPageResponse
import com.msg.network.api.UserAPI
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

    override fun inquiryMyPage(): Flow<InquiryMyPageResponse> = flow {
        emit(
            BitgoeulApiHandler<InquiryMyPageResponse>()
                .httpRequest { userAPI.inquiryMyPage() }
                .sendRequest()
        )
    }
}