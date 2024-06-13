package com.msg.network.datasource.admin

import com.msg.network.request.admin.GetUserListRequest
import com.msg.network.response.admin.UserListResponse
import kotlinx.coroutines.flow.Flow

interface AdminDataSource {
    fun getUserList(body: GetUserListRequest, keyword: String): Flow<UserListResponse>
}