package com.msg.network.datasource.admin

import com.msg.model.remote.request.admin.GetUserListRequest
import com.msg.model.remote.response.admin.UserListResponse
import kotlinx.coroutines.flow.Flow

interface AdminDataSource {
    suspend fun getUserList(body: GetUserListRequest, keyword: String): Flow<UserListResponse>
}