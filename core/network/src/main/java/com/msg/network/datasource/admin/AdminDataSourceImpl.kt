package com.msg.network.datasource.admin

import com.msg.network.api.AdminAPI
import com.msg.network.request.admin.GetUserListRequest
import com.msg.network.response.admin.UserListResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminDataSourceImpl @Inject constructor(
    private val adminAPI: AdminAPI,
) : AdminDataSource {
    override fun getUserList(
        body: GetUserListRequest,
        keyword: String,
    ): Flow<UserListResponse> =
        makeRequest { adminAPI.getUserList(body = body, keyword = keyword) }
}