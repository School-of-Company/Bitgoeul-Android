package com.msg.data.repository.admin

import com.msg.model.remote.request.admin.GetUserListRequest
import com.msg.model.remote.response.admin.UserListResponse
import kotlinx.coroutines.flow.Flow

interface AdminRepository {
    fun getUserList(body: GetUserListRequest, keyword: String): Flow<UserListResponse>
}