package com.msg.network.datasource.user

import com.msg.network.api.UserAPI
import com.msg.network.request.user.ChangePasswordRequest
import com.msg.network.response.user.InquiryMyPageResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userAPI: UserAPI
) : UserDataSource {
    override fun changePassword(body: ChangePasswordRequest): Flow<Unit> =
        makeRequest { userAPI.changePassword(body = body) }

    override fun inquiryMyPage(): Flow<InquiryMyPageResponse> =
        makeRequest { userAPI.inquiryMyPage() }
}