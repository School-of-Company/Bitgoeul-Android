package com.msg.data.repository.admin

import com.msg.model.remote.request.admin.GetUserListRequest
import com.msg.model.remote.response.admin.UserListResponse
import com.msg.network.datasource.admin.AdminDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminDataSource: AdminDataSource,
) : AdminRepository {
    override suspend fun getUserList(
        body: GetUserListRequest,
        keyword: String,
    ): Flow<UserListResponse> {
        return adminDataSource.getUserList(
            body = body,
            keyword = keyword
        )
    }
}